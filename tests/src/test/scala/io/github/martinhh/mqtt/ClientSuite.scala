package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.buffer.Buffer
import io.github.martinhh.mqtt.packet.PublishPacket
import munit.Location

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.UndefOr

abstract class ClientSuite(protocolVersion: ProtocolVersion) extends MQTTSuite {

  protected def firstMessageAsFuture(client: MqttClient): Future[PublishPacket] = {
    val p = scala.concurrent.Promise[PublishPacket]()
    client.on(EventType.Message) { (t, buffer, packet) =>
      assertEquals(t, packet.topic)
      assertEquals(buffer, packet.payload)
      assert(p.trySuccess(packet))
    }
    p.future
  }

  protected val defaultClient: FunFixture[MqttClient] = FunFixture.async(
    setup = _ => connectAsync(defaultHostClientOptions(protocolVersion)),
    teardown = _.endAsync()
  )

  protected val defaultClientViaUrlString: FunFixture[MqttClient] = FunFixture.async(
    setup = _ => connectAsync(wsBrokerUrl, ClientOptions(protocolVersion = protocolVersion)),
    teardown = _.endAsync()
  )

  protected val defaultTopic = s"io/github/martinhh/mqttv$protocolVersion/test/js"

  protected def testSubPubOneMessageNonRetained(client: MqttClient)(
    topic: String = defaultTopic,
    subOpts: UndefOr[ClientSubscribeOptions] = js.undefined
  )(message: String | Buffer, pubOpts: UndefOr[ClientPublishOptions] = js.undefined)(
    check: PublishPacket => Unit
  )(using Location): Unit =
    val received: Future[PublishPacket] = firstMessageAsFuture(client)
    for {
      _ <- client.subscribeAsync(topic)
      _ <- client.publishAsync(topic, message, pubOpts)
      receivedPacket <- received
      _ <- client.endAsync()
    } yield {
      check(receivedPacket)
    }

  defaultClient.test("basic publish and subscribe") { client =>
    val payload = "test"
    testSubPubOneMessageNonRetained(client)()(payload) { packet =>
      assertEquals(packet.payload.toString, payload)
      assertEquals(packet.topic, defaultTopic)
    }
  }

  defaultClientViaUrlString.test("basic publish and subscribe (connected using url-string)") {
    client =>
      val payload = "test"
      testSubPubOneMessageNonRetained(client)()(payload) { packet =>
        assertEquals(packet.payload.toString, payload)
        assertEquals(packet.topic, defaultTopic)
      }
  }

  defaultClient.test("subscribe after publish retained") { client =>
    val payload = "test"
    val topic: String = defaultTopic
    val received: Future[PublishPacket] = firstMessageAsFuture(client)
    for {
      _ <- client.publishAsync(topic, payload, ClientPublishOptions(retain = true))
      _ <- client.subscribeAsync(topic)
      receivedPacket <- received
      _ <- client.endAsync()
    } yield {
      assert(receivedPacket.retain)
    }
  }

}
