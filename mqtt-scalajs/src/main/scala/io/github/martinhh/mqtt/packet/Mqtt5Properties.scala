package io.github.martinhh.mqtt.packet

import io.github.martinhh.mqtt.Buffer

import scala.scalajs.js

trait Mqtt5Properties extends js.Object {
  def contentType: js.UndefOr[String] = js.undefined

  def correlationData: js.UndefOr[Buffer] = js.undefined

  def payloadFormatIndicator: js.UndefOr[Boolean] = js.undefined

  def responseTopic: js.UndefOr[String] = js.undefined

  // TODO: there are more members...
}

object Mqtt5Properties {

  def apply(
    contentType: js.UndefOr[String] = js.undefined,
    correlationData: js.UndefOr[Buffer] = js.undefined,
    payloadFormatIndicator: js.UndefOr[Boolean] = js.undefined,
    responseTopic: js.UndefOr[String] = js.undefined
  ): Mqtt5Properties =
    ImmutableMqtt5Properties(contentType, correlationData, payloadFormatIndicator, responseTopic)

  private class ImmutableMqtt5Properties(
    override val contentType: js.UndefOr[String],
    override val correlationData: js.UndefOr[Buffer],
    override val payloadFormatIndicator: js.UndefOr[Boolean],
    override val responseTopic: js.UndefOr[String]
  ) extends Mqtt5Properties
}
