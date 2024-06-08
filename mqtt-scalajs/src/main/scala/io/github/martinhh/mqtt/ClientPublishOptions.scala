package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.packet.Mqtt5Properties

import scala.scalajs.js

/**
 * Corresponds to mqtt-js `IClientPublishOptions`
 */
sealed trait ClientPublishOptions extends js.Object {
  def cbStorePut: js.UndefOr[StorePutCallback]

  def dup: js.UndefOr[Boolean]

  def properties: js.UndefOr[Mqtt5Properties]

  def qos: js.UndefOr[QoS]

  def retain: js.UndefOr[Boolean]
}

object ClientPublishOptions {

  def apply(
    cbStorePut: js.UndefOr[StorePutCallback] = js.undefined,
    dup: js.UndefOr[Boolean] = js.undefined,
    properties: js.UndefOr[Mqtt5Properties] = js.undefined,
    qos: js.UndefOr[QoS] = js.undefined,
    retain: js.UndefOr[Boolean] = js.undefined
  ): ClientPublishOptions = {
    val opts = new MutableClientPublishOptions {}
    cbStorePut.foreach(_ => opts.cbStorePut = cbStorePut)
    dup.foreach(_ => opts.dup = dup)
    properties.foreach(_ => opts.properties = properties)
    qos.foreach(_ => opts.qos = qos)
    retain.foreach(_ => opts.retain = retain)
    opts
  }

}

trait MutableClientPublishOptions extends ClientPublishOptions {
  var cbStorePut: js.UndefOr[StorePutCallback] = js.undefined

  var dup: js.UndefOr[Boolean] = js.undefined

  var properties: js.UndefOr[Mqtt5Properties] = js.undefined

  var qos: js.UndefOr[QoS] = js.undefined

  var retain: js.UndefOr[Boolean] = js.undefined
}
