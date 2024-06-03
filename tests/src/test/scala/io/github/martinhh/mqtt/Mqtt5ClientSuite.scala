package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.packet.Mqtt5Properties

class Mqtt5ClientSuite extends ClientSuite(5) {

  defaultClient.test("transmission of response-topic") { client =>
    val responseTopic = "the/response/topic"
    val properties = Mqtt5Properties(responseTopic = responseTopic)
    val publishOptions = ClientPublishOptions(properties = properties)
    testSubPubOneMessageNonRetained(client)()("", publishOptions) { packet =>
      assertEquals(packet.properties.flatMap(_.responseTopic), responseTopic)
    }
  }

  defaultClient.test("transmission of correlation data") { client =>
    val dataString = "the correlationData"
    val properties = Mqtt5Properties(correlationData = buffer.Buffer.from(dataString))
    val publishOptions = ClientPublishOptions(properties = properties)
    testSubPubOneMessageNonRetained(client)()("", publishOptions) { packet =>
      assertEquals(packet.properties.flatMap(_.correlationData.toString), dataString)
    }
  }

}
