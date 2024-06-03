package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.buffer.Buffer
import io.github.martinhh.mqtt.packet.PublishPacket
import io.github.martinhh.mqtt.packet.Packet
import io.github.martinhh.mqtt.packet.PartialDisconnectPacket

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.UndefOr

trait MqttClient {

  def publish(
    topic: String,
    message: String | Buffer,
    opts: UndefOr[ClientPublishOptions] = js.undefined,
    callback: UndefOr[PacketCallback] = js.undefined
  ): Unit

  def publishAsync(
    topic: String,
    message: String | Buffer,
    opts: UndefOr[ClientPublishOptions] = js.undefined
  ): Future[UndefOr[Packet]]

  def subscribe(
    topicObject: String | js.Array[String],
    opts: UndefOr[ClientSubscribeOptions] = js.undefined,
    callback: UndefOr[ClientSubscribeCallback] = js.undefined
  ): Unit

  def subscribeAsync(
    topicObject: String | js.Array[String],
    opts: UndefOr[ClientSubscribeOptions] = js.undefined
  ): Future[js.Array[SubscriptionGrant]]

  def unsubscribe(
    topic: String | js.Array[String],
    opts: UndefOr[ClientSubscribeOptions] = js.undefined,
    callback: UndefOr[ClientSubscribeCallback] = js.undefined
  ): Unit

  def unsubscribeAsync(
    topic: String | js.Array[String],
    opts: UndefOr[ClientSubscribeOptions] = js.undefined
  ): Future[UndefOr[Packet]]

  def connect(): Unit

  def reconnect(): Unit

  def end(
    force: UndefOr[Boolean] = js.undefined,
    opts: UndefOr[PartialDisconnectPacket] = js.undefined,
    cb: UndefOr[DoneCallback] = js.undefined
  ): Unit

  def endAsync(
    force: UndefOr[Boolean] = js.undefined,
    opts: UndefOr[PartialDisconnectPacket] = js.undefined
  ): Future[Unit]

  def removeOutgoingMessage(messageId: Int): Unit

  def handleMessage(packet: PublishPacket, callback: DoneCallback): Unit

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
      opts: UndefOr[ClientPublishOptions],
      callback: UndefOr[PacketCallback]
    ): Unit = underlying.publish(topic, message, opts, callback)

    override def publishAsync(
      topic: String,
      message: String | Buffer,
      opts: UndefOr[ClientPublishOptions]
    ): Future[UndefOr[Packet]] = underlying.publishAsync(topic, message, opts).toFuture

    override def subscribe(
      topicObject: String | js.Array[String],
      opts: UndefOr[ClientSubscribeOptions],
      callback: UndefOr[ClientSubscribeCallback]
    ): Unit = underlying.subscribe(topicObject, opts, callback)

    override def subscribeAsync(
      topicObject: String | js.Array[String],
      opts: UndefOr[ClientSubscribeOptions]
    ): Future[js.Array[SubscriptionGrant]] = underlying.subscribeAsync(topicObject, opts).toFuture

    override def unsubscribe(
      topic: String | js.Array[String],
      opts: UndefOr[ClientSubscribeOptions],
      callback: UndefOr[ClientSubscribeCallback]
    ): Unit = underlying.unsubscribe(topic, opts, callback)

    override def unsubscribeAsync(
      topic: String | js.Array[String],
      opts: UndefOr[ClientSubscribeOptions]
    ): Future[UndefOr[Packet]] = underlying.unsubscribeAsync(topic, opts).toFuture

    override def connect(): Unit = underlying.connect()

    override def reconnect(): Unit = underlying.reconnect()

    override def end(
      force: UndefOr[Boolean],
      opts: UndefOr[PartialDisconnectPacket],
      cb: UndefOr[DoneCallback]
    ): Unit = underlying.end(force, opts, cb)

    override def endAsync(
      force: UndefOr[Boolean],
      opts: UndefOr[PartialDisconnectPacket]
    ): Future[Unit] = {
      val p = scala.concurrent.Promise[Unit]()
      val doneCallback: DoneCallback = maybeError => {
        maybeError.fold[Unit] {
          p.success(())
        } { e =>
          p.failure(js.special.wrapAsThrowable(e))
        }
      }
      end(force, opts, doneCallback)
      p.future
    }

    override def removeOutgoingMessage(messageId: Int): Unit =
      underlying.removeOutgoingMessage(messageId)

    override def handleMessage(packet: PublishPacket, callback: DoneCallback): Unit =
      underlying.handleMessage(packet, callback)

    override def connected: Boolean = underlying.connected

    override def reconnecting: Boolean = underlying.reconnecting

    override def getLastMessageId: Int = underlying.getLastMessageId()

    override def on[S <: String, C <: js.Function](eventType: EventType[S, C])(callback: C): Unit =
      underlying.onUnsafe(eventType.stringId, callback)
  }

  private[mqtt] def apply(delegate: jsnative.MqttClient): MqttClient = MqttClientImpl(delegate)
}
