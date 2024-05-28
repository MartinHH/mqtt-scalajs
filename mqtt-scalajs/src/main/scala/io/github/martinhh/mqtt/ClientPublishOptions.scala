package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.packet.Mqtt5Properties

import scala.scalajs.js

/**
 * Corresponds to mqtt-js `IClientPublishOptions`
 */
trait ClientPublishOptions extends js.Object {
  def cbStorePut: js.UndefOr[StorePutCallback] = js.undefined

  def dup: js.UndefOr[Boolean] = js.undefined

  def properties: js.UndefOr[Mqtt5Properties] = js.undefined

  def qos: js.UndefOr[QoS] = js.undefined

  def retain: js.UndefOr[Boolean] = js.undefined
}

object ClientPublishOptions {

  def apply(
    cbStorePut: js.UndefOr[StorePutCallback] = js.undefined,
    dup: js.UndefOr[Boolean] = js.undefined,
    properties: js.UndefOr[Mqtt5Properties] = js.undefined,
    qos: js.UndefOr[QoS] = js.undefined,
    retain: js.UndefOr[Boolean] = js.undefined
  ): ClientPublishOptions =
    ImmutableClientPublishOptions(cbStorePut, dup, properties, qos, retain)

  private class ImmutableClientPublishOptions(
    override val cbStorePut: js.UndefOr[StorePutCallback],
    override val dup: js.UndefOr[Boolean],
    override val properties: js.UndefOr[Mqtt5Properties],
    override val qos: js.UndefOr[QoS],
    override val retain: js.UndefOr[Boolean]
  ) extends ClientPublishOptions

}
