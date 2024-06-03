package io.github.martinhh.mqtt.buffer.jsnative

import io.github.martinhh.mqtt.buffer.Buffer.Encoding

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.typedarray.ArrayBuffer
import scala.scalajs.js.typedarray.Uint8Array

/**
 * Facade for Node.js buffer.
 */
@js.native
trait Buffer extends Uint8Array {

  // TODO: there are many more methods...

  def readInt32BE(offset: js.UndefOr[Int] = js.undefined): Int = js.native

  def readInt32LE(offset: js.UndefOr[Int] = js.undefined): Int = js.native

  def writeInt32BE(value: Int, offset: js.UndefOr[Int] = js.undefined): Int = js.native

  def writeInt32LE(value: Int, offset: js.UndefOr[Int] = js.undefined): Int = js.native

  def toString(
    encoding: Encoding,
    start: js.UndefOr[Int] = js.undefined,
    end: js.UndefOr[Int] = js.undefined
  ): String = js.native
}

@js.native
@JSGlobal
private[buffer] object Buffer extends js.Object {

  def from(
    arrayBuffer: ArrayBuffer,
    byteOffset: js.UndefOr[Int] = js.undefined,
    length: js.UndefOr[Int] = js.undefined
  ): Buffer = js.native

  def from(from: String | js.Array[Short] | Buffer | Uint8Array): Buffer = js.native

  def from(string: String, encoding: Encoding): Buffer = js.native

  def alloc(
    size: Int,
    fill: js.UndefOr[String | Buffer | Uint8Array | Byte] = js.native,
    encoding: js.UndefOr[Encoding] = js.native
  ): Buffer = js.native

  def isBuffer(obj: js.Any): Boolean = js.native
}
