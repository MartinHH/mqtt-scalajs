package io.github.martinhh.mqtt

import scala.scalajs.js

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
