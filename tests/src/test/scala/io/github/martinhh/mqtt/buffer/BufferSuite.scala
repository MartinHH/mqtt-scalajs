package io.github.martinhh.mqtt.buffer

import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import scommons.nodejs
import org.scalacheck.Prop

object BufferSuite {
  val genUtfEncoding: Gen[Buffer.Encoding] =
    Gen.oneOf(
      Seq[Buffer.Encoding](
        "utf16le",
        "utf8"
      )
    )
}

class BufferSuite extends munit.ScalaCheckSuite {
  import BufferSuite.*

  test("Buffer is scommons.nodejs.Buffer") {
    assert(scommons.nodejs.Buffer.isBuffer(Buffer.from("foo")))
  }

  test("scommons.nodejs.Buffer is Buffer") {
    assert(Buffer.isBuffer(scommons.nodejs.Buffer.from("foo")))
  }

  property("from(x: String).toString === x") {
    Prop.forAll { (x: String) =>
      assertEquals(Buffer.from(x).toString, x)
    }
  }

  property("from(x: String, encoding).toString(encoding) === x (for all utf-encodings)") {
    Prop.forAll(Arbitrary.arbitrary[String], genUtfEncoding) {
      (x: String, encoding: Buffer.Encoding) =>
        assertEquals(Buffer.from(x, encoding).toString(encoding), x)
    }
  }

  property("from(x: String, ascii).toString(ascii) === x (for ascii strings)") {
    val ascii: Buffer.Encoding = "ascii"
    Prop.forAll(Gen.asciiStr) { (x: String) =>
      assertEquals(Buffer.from(x, ascii).toString(ascii), x)
    }
  }

  property("readInt32BE(writeInt32BE(x)) === x") {
    Prop.forAll(Arbitrary.arbitrary[Int], Gen.choose(0, 20)) { (x: Int, offset: Int) =>
      val buffer: Buffer = Buffer.alloc(offset + 4)
      buffer.writeInt32BE(x, offset)
      assertEquals(buffer.readInt32BE(offset), x)
    }
  }

  property("readInt32LE(writeInt32LE(x)) === x") {
    Prop.forAll(Arbitrary.arbitrary[Int], Gen.choose(0, 20)) { (x: Int, offset: Int) =>
      val buffer: Buffer = Buffer.alloc(offset + 4)
      buffer.writeInt32LE(x, offset)
      assertEquals(buffer.readInt32LE(offset), x)
    }
  }

  test("alloc and fill with 0") {
    val buffer: Buffer = Buffer.alloc(4, 0.toByte)
    assertEquals(buffer.readInt32BE(), 0)
  }

  test("alloc and fill with 1") {
    val buffer: Buffer = Buffer.alloc(4, 1.toByte)
    assertEquals(buffer.readInt32BE(), 0x01010101)
  }

  test("alloc and fill with 255") {
    val buffer: Buffer = Buffer.alloc(4, 255.toByte)
    assertEquals(buffer.readInt32BE(), 0xffffffff)
  }

}
