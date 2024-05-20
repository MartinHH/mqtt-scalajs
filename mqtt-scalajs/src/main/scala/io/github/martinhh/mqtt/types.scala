package io.github.martinhh.mqtt

import scala.scalajs.js.typedarray.Uint8Array

/**
 * 3=MQTT 3.1 4=MQTT 3.1.1 5=MQTT 5.0.
 */
type ProtocolVersion = 4 | 5 | 3

type QoS = 0 | 1 | 2

opaque type Buffer <: Uint8Array = Uint8Array
