package io.github.martinhh.mqtt.buffer

import scala.scalajs.js
import scala.scalajs.js.typedarray.ArrayBuffer
import scala.scalajs.js.typedarray.Uint8Array

/**
 * Facade for Node.js buffer.
 */
type Buffer = jsnative.Buffer

object Buffer {
  def from(
    arrayBuffer: ArrayBuffer,
    byteOffset: js.UndefOr[Int] = js.undefined,
    length: js.UndefOr[Int] = js.undefined
  ): Buffer = jsnative.Buffer.from(arrayBuffer, byteOffset, length)

  def from(from: String | js.Array[Short] | Buffer | Uint8Array): Buffer =
    jsnative.Buffer.from(from)

  def from(string: String, encoding: Encoding): Buffer =
    jsnative.Buffer.from(string, encoding)

  def alloc(
    size: Int,
    fill: js.UndefOr[String | Buffer | Uint8Array | Byte] = js.undefined,
    encoding: js.UndefOr[Encoding] = js.undefined
  ): Buffer = jsnative.Buffer.alloc(size, fill, encoding)

  def isBuffer(obj: js.Any): Boolean = jsnative.Buffer.isBuffer(obj)

  type Encoding = "ascii" | "base64" | "base64url" | "hex" | "latin1" | "utf16le" | "utf8"
}
