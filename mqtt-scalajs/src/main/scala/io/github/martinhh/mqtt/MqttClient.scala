package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.packet.IPublishPacket
import io.github.martinhh.mqtt.packet.Packet
import io.github.martinhh.mqtt.packet.PartialIDisconnectPacket

import scala.scalajs.js
import scala.scalajs.js.Promise
import scala.scalajs.js.UndefOr

trait MqttClient {

  def publish(
    topic: String,
    message: String | Buffer,
    opts: UndefOr[IClientPublishOptions] = js.undefined,
    callback: UndefOr[PacketCallback] = js.undefined
  ): Unit

  def publishAsync(
    topic: String,
    message: String,
    opts: UndefOr[IClientPublishOptions] = js.undefined
  ): js.Promise[UndefOr[Packet]]

  def subscribe(
    topicObject: String | js.Array[String],
    opts: UndefOr[IClientSubscribeOptions] = js.undefined,
    callback: UndefOr[ClientSubscribeCallback] = js.undefined
  ): Unit

  def subscribeAsync(
    topicObject: String | js.Array[String],
    opts: UndefOr[IClientSubscribeOptions] = js.undefined
  ): js.Promise[js.Array[ISubscriptionGrant]]

  def unsubscribe(
    topic: String | js.Array[String],
    opts: UndefOr[IClientSubscribeOptions] = js.undefined,
    callback: UndefOr[ClientSubscribeCallback] = js.undefined
  ): Unit

  def unsubscribeAsync(
    topic: String | js.Array[String],
    opts: UndefOr[IClientSubscribeOptions] = js.undefined
  ): js.Promise[UndefOr[Packet]]

  def connect(): Unit

  def reconnect(): Unit

  def end(
    force: UndefOr[Boolean] = js.undefined,
    opts: UndefOr[PartialIDisconnectPacket] = js.undefined,
    cb: UndefOr[DoneCallback] = js.undefined
  ): Unit

  def removeOutgoingMessage(messageId: Int): Unit

  def handleMessage(packet: IPublishPacket, callback: DoneCallback): Unit

  def connected: Boolean

  def reconnecting: Boolean

  def getLastMessageId: Int

  def on[S <: String, C <: js.Function](eventType: EventType[S, C])(callback: C): Unit

}

object MqttClient {
  private class MqttClientImpl(private val underlying: jsnative.MqttClient) extends MqttClient {

    override def publish(
      topic: String,
      message: String | Buffer,
      opts: UndefOr[IClientPublishOptions],
      callback: UndefOr[PacketCallback]
    ): Unit = underlying.publish(topic, message, opts, callback)

    override def publishAsync(
      topic: String,
      message: String,
      opts: UndefOr[IClientPublishOptions]
    ): Promise[UndefOr[Packet]] = underlying.publishAsync(topic, message, opts)

    override def subscribe(
      topicObject: String | js.Array[String],
      opts: UndefOr[IClientSubscribeOptions],
      callback: UndefOr[ClientSubscribeCallback]
    ): Unit = underlying.subscribe(topicObject, opts, callback)

    override def subscribeAsync(
      topicObject: String | js.Array[String],
      opts: UndefOr[IClientSubscribeOptions]
    ): Promise[js.Array[ISubscriptionGrant]] = underlying.subscribeAsync(topicObject, opts)

    override def unsubscribe(
      topic: String | js.Array[String],
      opts: UndefOr[IClientSubscribeOptions],
      callback: UndefOr[ClientSubscribeCallback]
    ): Unit = underlying.unsubscribe(topic, opts, callback)

    override def unsubscribeAsync(
      topic: String | js.Array[String],
      opts: UndefOr[IClientSubscribeOptions]
    ): Promise[UndefOr[Packet]] = underlying.unsubscribeAsync(topic, opts)

    override def connect(): Unit = underlying.connect()

    override def reconnect(): Unit = underlying.reconnect()

    override def end(
      force: UndefOr[Boolean],
      opts: UndefOr[PartialIDisconnectPacket],
      cb: UndefOr[DoneCallback]
    ): Unit = underlying.end(force, opts, cb)

    override def removeOutgoingMessage(messageId: Int): Unit =
      underlying.removeOutgoingMessage(messageId)

    override def handleMessage(packet: IPublishPacket, callback: DoneCallback): Unit =
      underlying.handleMessage(packet, callback)

    override def connected: Boolean = underlying.connected

    override def reconnecting: Boolean = underlying.reconnecting

    override def getLastMessageId: Int = underlying.getLastMessageId()

    override def on[S <: String, C <: js.Function](eventType: EventType[S, C])(callback: C): Unit =
      underlying.onUnsafe(eventType.stringId, callback)
  }

  private[mqtt] def apply(delegate: jsnative.MqttClient): MqttClient = MqttClientImpl(delegate)
}
