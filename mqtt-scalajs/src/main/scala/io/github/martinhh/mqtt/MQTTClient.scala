package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.packet.IPublishPacket
import io.github.martinhh.mqtt.packet.Packet
import io.github.martinhh.mqtt.packet.PartialIDisconnectPacket

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName


extension (client: MqttClient) {
  def on[S <: String, C <: js.Function](eventType: EventType[S, C])(callback: C): client.type =
    client.onUnsafe(eventType.stringId, callback)
}

@js.native
trait MqttClient extends js.Object {

  @JSName("on")
  def onUnsafe[Event <: String](event: Event, callback: js.Function): this.type = js.native

  def publish(
    topic: String,
    message: String | Buffer,
    opts: js.UndefOr[IClientPublishOptions] = js.undefined,
    callback: js.UndefOr[PacketCallback] = js.undefined
  ): MqttClient = js.native

  def publishAsync(
    topic: String,
    message: String,
    opts: js.UndefOr[IClientPublishOptions] = js.undefined
  ): js.Promise[js.UndefOr[Packet]] = js.native

  def subscribe(
    topicObject: String | js.Array[String],
    opts: js.UndefOr[IClientSubscribeOptions] = js.undefined,
    callback: js.UndefOr[ClientSubscribeCallback] = js.undefined
  ): MqttClient = js.native

  def subscribeAsync(
    topicObject: String | js.Array[String],
    opts: js.UndefOr[IClientSubscribeOptions] = js.undefined
  ): js.Promise[js.Array[ISubscriptionGrant]] = js.native

  def unsubscribe(
    topic: String | js.Array[String],
    opts: js.UndefOr[IClientSubscribeOptions] = js.undefined,
    callback: js.UndefOr[ClientSubscribeCallback] = js.undefined
  ): MqttClient = js.native

  def unsubscribeAsync(
    topic: String | js.Array[String],
    opts: js.UndefOr[IClientSubscribeOptions] = js.undefined
  ): js.Promise[js.UndefOr[Packet]] = js.native

  def connect(): MqttClient = js.native

  def reconnect(): MqttClient = js.native

  def end(
    force: js.UndefOr[Boolean] = js.undefined,
    opts: js.UndefOr[PartialIDisconnectPacket] = js.undefined,
    cb: js.UndefOr[DoneCallback] = js.undefined
  ): MqttClient = js.native

  def removeOutgoingMessage(messageId: Int): MqttClient = js.native

  def handleMessage(packet: IPublishPacket, callback: DoneCallback): Unit = js.native

  def connected: Boolean = js.native

  def reconnecting: Boolean = js.native

  def getLastMessageId(): Int = js.native

}
