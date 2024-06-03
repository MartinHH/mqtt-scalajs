package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.buffer.Buffer
import io.github.martinhh.mqtt.packet.PublishPacket
import munit.Location

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.UndefOr

abstract class ClientSuite(protocolVersion: ProtocolVersion) extends MQTTSuite {

  protected val defaultClient: FunFixture[MqttClient] = FunFixture.async(
    setup = _ => connectAsync(wsBrokerUrl, ClientOptions(protocolVersion)),
    teardown = _.endAsync()
  )

  protected val defaultTopic = s"io/github/martinhh/mqttv$protocolVersion/test/js"

  protected def testSubPubOneMessageNonRetained(client: MqttClient)(
    topic: String = defaultTopic,
    subOpts: UndefOr[ClientSubscribeOptions] = js.undefined
  )(message: String | Buffer, pubOpts: UndefOr[ClientPublishOptions] = js.undefined)(
    check: PublishPacket => Unit
  )(using Location): Unit =
    val received: Future[PublishPacket] = {
      val p = scala.concurrent.Promise[PublishPacket]()
      client.on(EventType.Message) { (t, buffer, packet) =>
        assertEquals(t, topic)
        assertEquals(t, packet.topic)
        assertEquals(buffer, packet.payload)
        p.trySuccess(packet)
      }
      p.future
    }
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
    }
  }

}
