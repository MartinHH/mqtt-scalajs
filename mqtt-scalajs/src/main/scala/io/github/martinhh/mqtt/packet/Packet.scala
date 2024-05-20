package io.github.martinhh.mqtt.packet

import io.github.martinhh.mqtt.Buffer
import io.github.martinhh.mqtt.QoS

import scala.scalajs.js

sealed trait Packet extends js.Object

trait IConnackPacket extends Packet

trait IDisconnectPacket extends Packet

trait IPublishPacket extends Packet {
  def dup: Boolean

  def payload: String | Buffer

  def properties: js.UndefOr[CorrelationData] = js.undefined

  def qos: QoS

  def retain: Boolean

  def topic: String
}

trait PartialIDisconnectPacket extends js.Object
