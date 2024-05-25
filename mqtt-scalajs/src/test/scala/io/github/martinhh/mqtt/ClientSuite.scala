package io.github.martinhh.mqtt

class ClientSuite extends MQTTSuite {

  test("publish") {
    val connectOptions = ClientOptions(5)
    for {
      client <- connectAsync(wsBrokerUrl, connectOptions)
      topic = "io/github/martinhh/mqtt/test/js"
      promise = {
        val p = scala.concurrent.Promise[String]()
        client.on(EventType.Message) { (t, buffer, _) =>
          assertEquals(t, topic)
          p.success(buffer.toString)
        }
        p
      }
      payload = "test"
      _ <- client.subscribeAsync(topic)
      pc <- client.publishAsync(topic, payload)
      received <- promise.future
      _ <- client.endAsync()
    } yield {
      assertEquals(received, payload)
    }
  }
}
