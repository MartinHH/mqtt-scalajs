package io.github.martinhh.mqtt

import scala.scalajs.js

trait ReasonString extends js.Object {
  // TODO...
}

trait IClientSubscribeProperties extends js.Object {

  var properties: js.UndefOr[ReasonString] = js.undefined
}

trait IClientSubscribeOptions extends IClientSubscribeProperties {
  def nl: js.UndefOr[Boolean] = js.undefined

  def qos: QoS

  def rap: js.UndefOr[Boolean] = js.undefined

  def rh: js.UndefOr[Double] = js.undefined

  def topic: String
}

trait ISubscriptionGrant extends js.Object {
  def nl: js.UndefOr[Boolean] = js.undefined

  def qos: QoS | 128

  def rap: js.UndefOr[Boolean] = js.undefined

  def rh: js.UndefOr[Double] = js.undefined

  def topic: String
}
