package io.github.martinhh.mqtt.packet

import io.github.martinhh.mqtt.Buffer
import io.github.martinhh.mqtt.QoS

import scala.scalajs.js

sealed trait Packet extends js.Object

/**
 * Corresponds to mqtt-js `IConnackPacket`
 */
trait ConnackPacket extends Packet

/**
 * Corresponds to mqtt-js `IDisconnectPacket`
 */
trait DisconnectPacket extends Packet

/**
 * Corresponds to mqtt-js `IPublishPacket`
 */
trait PublishPacket extends Packet {
  def dup: Boolean

  def payload: String | Buffer

  def properties: js.UndefOr[CorrelationData] = js.undefined

  def qos: QoS

  def retain: Boolean

  def topic: String
}

/**
 * Corresponds to mqtt-js `Partial<IDisconnectPacket>`
 */
trait PartialDisconnectPacket extends js.Object
