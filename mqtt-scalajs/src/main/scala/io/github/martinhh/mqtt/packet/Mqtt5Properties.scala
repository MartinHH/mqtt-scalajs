package io.github.martinhh.mqtt.packet

import io.github.martinhh.mqtt.buffer.Buffer

import scala.scalajs.js

sealed trait Mqtt5Properties extends js.Object {
  def contentType: js.UndefOr[String]

  def correlationData: js.UndefOr[Buffer]

  def payloadFormatIndicator: js.UndefOr[Boolean]

  def responseTopic: js.UndefOr[String]

  // TODO: there are more members...
}

object Mqtt5Properties {

  def apply(
    contentType: js.UndefOr[String] = js.undefined,
    correlationData: js.UndefOr[Buffer] = js.undefined,
    payloadFormatIndicator: js.UndefOr[Boolean] = js.undefined,
    responseTopic: js.UndefOr[String] = js.undefined
  ): Mqtt5Properties = {
    val props = new MutableMqtt5Properties {}
    contentType.foreach(_ => props.contentType = contentType)
    correlationData.foreach(_ => props.correlationData = correlationData)
    payloadFormatIndicator.foreach(_ => props.payloadFormatIndicator = payloadFormatIndicator)
    responseTopic.foreach(_ => props.responseTopic = responseTopic)
    props
  }
}

trait MutableMqtt5Properties extends Mqtt5Properties {
  var contentType: js.UndefOr[String] = js.undefined

  var correlationData: js.UndefOr[Buffer] = js.undefined

  var payloadFormatIndicator: js.UndefOr[Boolean] = js.undefined

  var responseTopic: js.UndefOr[String] = js.undefined
}
