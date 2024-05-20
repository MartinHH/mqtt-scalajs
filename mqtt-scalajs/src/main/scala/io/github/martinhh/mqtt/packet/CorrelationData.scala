package io.github.martinhh.mqtt.packet

import io.github.martinhh.mqtt.Buffer

import scala.scalajs.js

trait CorrelationData extends js.Object {
  def contentType: js.UndefOr[String] = js.undefined

  def correlationData: js.UndefOr[Buffer] = js.undefined

  def payloadFormatIndicator: js.UndefOr[Boolean] = js.undefined

  def responseTopic: js.UndefOr[String] = js.undefined

  // TODO: there are more members...
}
