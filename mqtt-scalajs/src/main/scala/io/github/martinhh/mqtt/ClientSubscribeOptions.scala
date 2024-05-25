package io.github.martinhh.mqtt

import scala.scalajs.js

trait ReasonString extends js.Object {
  // TODO...
}

/**
 * Corresponds to mqtt-js `IClientSubscribeProperties`
 */
trait ClientSubscribeProperties extends js.Object {

  var properties: js.UndefOr[ReasonString] = js.undefined
}

/**
 * Corresponds to mqtt-js `IClientSubscribeOptions`
 */
trait ClientSubscribeOptions extends ClientSubscribeProperties {
  def nl: js.UndefOr[Boolean] = js.undefined

  def qos: QoS

  def rap: js.UndefOr[Boolean] = js.undefined

  def rh: js.UndefOr[Double] = js.undefined

  def topic: String
}

/**
 * Corresponds to mqtt-js `ISubscriptionGrant`
 */
trait SubscriptionGrant extends js.Object {
  def nl: js.UndefOr[Boolean] = js.undefined

  def qos: QoS | 128

  def rap: js.UndefOr[Boolean] = js.undefined

  def rh: js.UndefOr[Double] = js.undefined

  def topic: String
}
