package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.packet.CorrelationData

import scala.scalajs.js

trait IClientPublishOptions extends js.Object {
  def cbStorePut: js.UndefOr[StorePutCallback] = js.undefined

  def dup: js.UndefOr[Boolean] = js.undefined

  def properties: js.UndefOr[CorrelationData] = js.undefined

  def qos: js.UndefOr[QoS] = js.undefined

  def retain: js.UndefOr[Boolean] = js.undefined
}
