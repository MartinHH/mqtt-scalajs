package io.github.martinhh.mqtt

abstract class ClientSuite(protocolVersion: ProtocolVersion) extends MQTTSuite {

  protected val defaultClient: FunFixture[MqttClient] = FunFixture.async(
    setup = _ => connectAsync(wsBrokerUrl, ClientOptions(protocolVersion)),
    teardown = _.endAsync()
  )

  defaultClient.test("publish") { client =>
    val topic = s"io/github/martinhh/mqttv$protocolVersion/test/js"
    val promise = {
      val p = scala.concurrent.Promise[String]()
      client.on(EventType.Message) { (t, buffer, _) =>
        assertEquals(t, topic)
        p.success(buffer.toString)
      }
      p
    }
    val payload = "test"
    for {
      _ <- client.subscribeAsync(topic)
      _ <- client.publishAsync(topic, payload)
      received <- promise.future
      _ <- client.endAsync()
    } yield {
      assertEquals(received, payload)
    }
  }
}
