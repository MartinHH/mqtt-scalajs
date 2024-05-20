package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.ClientOptions
import io.github.martinhh.mqtt.MqttClient

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("mqtt", JSImport.Default)
private object MQTT extends js.Object {

  def connect(brokerUrl: String): MqttClient = js.native
  def connect(brokerUrl: String, opts: ClientOptions): MqttClient = js.native
}

inline def connect(brokerUrl: String): MqttClient =
  MQTT.connect(brokerUrl)

inline def connect(brokerUrl: String, opts: ClientOptions): MqttClient =
  MQTT.connect(brokerUrl, opts)
