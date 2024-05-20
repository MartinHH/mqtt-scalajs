package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.packet.*

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

type VoidCallback = js.Function0[Unit]

type OnCloseCallback = VoidCallback

type OnConnectCallback = js.Function1[IConnackPacket, Unit]

type OnDisconnectCallback = js.Function1[IDisconnectPacket, Unit]

type OnErrorCallback = js.Function1[js.Error /* | ErrorWithReasonCode */, Unit]

type OnMessageCallback = js.Function3[String, Buffer, IPublishPacket, Unit]

type OnPacketCallback = js.Function1[Packet, Unit]

type PacketCallback = js.Function2[js.UndefOr[js.Error], js.UndefOr[Packet], Any]

type ClientSubscribeCallback =
  js.Function2[js.UndefOr[js.Error], js.UndefOr[js.Array[ISubscriptionGrant]], Unit]

type DoneCallback = js.Function1[js.UndefOr[js.Error], Unit]

type StorePutCallback = VoidCallback

sealed trait EventType[S <: String: ValueOf, C <: js.Function] {

  inline def stringId: String = summon[ValueOf[S]].value
}

object EventType {
  case object Connect extends EventType["connect", OnConnectCallback]
  case object Reconnect extends EventType["reconnect", VoidCallback]
  case object Close extends EventType["close", OnCloseCallback]
  case object Disconnect extends EventType["disconnect", OnDisconnectCallback]
  case object Offline extends EventType["offline", OnDisconnectCallback]
  case object Error extends EventType["error", OnErrorCallback]
  case object End extends EventType["end", VoidCallback]
  case object Message extends EventType["message", OnMessageCallback]
  case object PacketSend extends EventType["packetsend", OnPacketCallback]
  case object PacketReceive extends EventType["packetreceive", OnPacketCallback]
}

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
