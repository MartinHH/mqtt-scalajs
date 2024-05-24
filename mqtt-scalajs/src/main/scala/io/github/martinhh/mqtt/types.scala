package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.packet.IConnackPacket
import io.github.martinhh.mqtt.packet.IDisconnectPacket
import io.github.martinhh.mqtt.packet.IPublishPacket
import io.github.martinhh.mqtt.packet.Packet

import scala.scalajs.js
import scala.scalajs.js.typedarray.Uint8Array

/**
 * 3=MQTT 3.1 4=MQTT 3.1.1 5=MQTT 5.0.
 */
type ProtocolVersion = 4 | 5 | 3

type QoS = 0 | 1 | 2

opaque type Buffer <: Uint8Array = Uint8Array


type VoidCallback = js.Function0[Unit]

type OnCloseCallback = VoidCallback

type OnConnectCallback = js.Function1[IConnackPacket, Unit]

type OnDisconnectCallback = js.Function1[IDisconnectPacket, Unit]

type OnErrorCallback = js.Function1[js.Error /* | ErrorWithReasonCode */ , Unit]

type OnMessageCallback = js.Function3[String, Buffer, IPublishPacket, Unit]

type OnPacketCallback = js.Function1[Packet, Unit]

type PacketCallback = js.Function2[js.UndefOr[js.Error], js.UndefOr[Packet], Any]

type ClientSubscribeCallback =
  js.Function2[js.UndefOr[js.Error], js.UndefOr[js.Array[ISubscriptionGrant]], Unit]

type DoneCallback = js.Function1[js.UndefOr[js.Error], Unit]

type StorePutCallback = VoidCallback
