package io.github.martinhh.mqtt.jsnative

import io.github.martinhh.mqtt.Buffer
import io.github.martinhh.mqtt.ClientSubscribeCallback
import io.github.martinhh.mqtt.DoneCallback
import io.github.martinhh.mqtt.ClientPublishOptions
import io.github.martinhh.mqtt.ClientSubscribeOptions
import io.github.martinhh.mqtt.SubscriptionGrant
import io.github.martinhh.mqtt.PacketCallback
import io.github.martinhh.mqtt.packet.PublishPacket
import io.github.martinhh.mqtt.packet.Packet
import io.github.martinhh.mqtt.packet.PartialDisconnectPacket

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
private[mqtt] trait MqttClient extends js.Object {

  @JSName("on")
  def onUnsafe[Event <: String](event: Event, callback: js.Function): this.type = js.native

  def publish(
    topic: String,
    message: String | Buffer,
    opts: js.UndefOr[ClientPublishOptions] = js.undefined,
    callback: js.UndefOr[PacketCallback] = js.undefined
  ): MqttClient = js.native

  def publishAsync(
    topic: String,
    message: String,
    opts: js.UndefOr[ClientPublishOptions] = js.undefined
  ): js.Promise[js.UndefOr[Packet]] = js.native

  def subscribe(
    topicObject: String | js.Array[String],
    opts: js.UndefOr[ClientSubscribeOptions] = js.undefined,
    callback: js.UndefOr[ClientSubscribeCallback] = js.undefined
  ): MqttClient = js.native

  def subscribeAsync(
    topicObject: String | js.Array[String],
    opts: js.UndefOr[ClientSubscribeOptions] = js.undefined
  ): js.Promise[js.Array[SubscriptionGrant]] = js.native

  def unsubscribe(
    topic: String | js.Array[String],
    opts: js.UndefOr[ClientSubscribeOptions] = js.undefined,
    callback: js.UndefOr[ClientSubscribeCallback] = js.undefined
  ): MqttClient = js.native

  def unsubscribeAsync(
    topic: String | js.Array[String],
    opts: js.UndefOr[ClientSubscribeOptions] = js.undefined
  ): js.Promise[js.UndefOr[Packet]] = js.native

  def connect(): MqttClient = js.native

  def reconnect(): MqttClient = js.native

  def end(
    force: js.UndefOr[Boolean] = js.undefined,
    opts: js.UndefOr[PartialDisconnectPacket] = js.undefined,
    cb: js.UndefOr[DoneCallback] = js.undefined
  ): MqttClient = js.native

  def removeOutgoingMessage(messageId: Int): MqttClient = js.native

  def handleMessage(packet: PublishPacket, callback: DoneCallback): Unit = js.native

  def connected: Boolean = js.native

  def reconnecting: Boolean = js.native

  def getLastMessageId(): Int = js.native

}
