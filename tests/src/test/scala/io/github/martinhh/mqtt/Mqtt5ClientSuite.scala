package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.packet.Mqtt5Properties

class Mqtt5ClientSuite extends ClientSuite(5) {

  defaultClient.test("response-topic transmission") { client =>
    val responseTopic = "the/response/topic"
    val properties = Mqtt5Properties(responseTopic = responseTopic)
    val publishOptions = ClientPublishOptions(properties = properties)
    testSubPubOneMessageNonRetained(client)()("", publishOptions) { packet =>
      assertEquals(packet.properties.flatMap(_.responseTopic), responseTopic)
    }
  }

}
