package io.github.martinhh.mqtt

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("mqtt", JSImport.Default)
private object MQTT extends js.Object {

  def connect(
    brokerUrl: String,
    opts: js.UndefOr[ClientOptions] = js.undefined
  ): jsnative.MqttClient =
    js.native

  def connectAsync(
    brokerUrl: String,
    opts: js.UndefOr[ClientOptions] = js.undefined
  ): js.Promise[jsnative.MqttClient] =
    js.native
}

inline def connect(brokerUrl: String, opts: js.UndefOr[ClientOptions] = js.undefined): MqttClient =
  MqttClient(MQTT.connect(brokerUrl, opts))

// TODO:
//inline def connect(opts: ClientOptions): MqttClient =
//    MQTT.connect(opts)

inline def connectAsync(
  brokerUrl: String,
  opts: js.UndefOr[ClientOptions] = js.undefined
): js.Promise[MqttClient] =
  MQTT.connectAsync(brokerUrl, opts).`then`(client => MqttClient(client))

// TODO:
//inline def connectAsync(opts: ClientOptions): MqttClient =
//    MQTT.connectAsync(opts)
