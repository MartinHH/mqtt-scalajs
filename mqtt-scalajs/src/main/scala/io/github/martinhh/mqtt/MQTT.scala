package io.github.martinhh.mqtt

import scala.concurrent.Future
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

  def connect(
    opts: ClientOptions
  ): jsnative.MqttClient =
    js.native

  def connectAsync(
    brokerUrl: String,
    opts: js.UndefOr[ClientOptions] = js.undefined
  ): js.Promise[jsnative.MqttClient] =
    js.native

  def connectAsync(
    opts: ClientOptions
  ): js.Promise[jsnative.MqttClient] =
    js.native
}

inline def connect(brokerUrl: String, opts: js.UndefOr[ClientOptions] = js.undefined): MqttClient =
  MqttClient(MQTT.connect(brokerUrl, opts))

inline def connect(opts: ClientOptions): MqttClient =
  MqttClient(MQTT.connect(opts))

inline def connectAsync(
  brokerUrl: String,
  opts: js.UndefOr[ClientOptions] = js.undefined
): Future[MqttClient] =
  MQTT.connectAsync(brokerUrl, opts).`then`(client => MqttClient(client)).toFuture

inline def connectAsync(opts: ClientOptions): Future[MqttClient] =
  MQTT.connectAsync(opts).`then`(client => MqttClient(client)).toFuture
