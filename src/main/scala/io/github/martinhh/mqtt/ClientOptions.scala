package io.github.martinhh.mqtt

import scala.scalajs.js

type UserProperties = js.Dictionary[String | js.Array[String]]

trait ClientOptions extends js.Object {

  /** Defaults to 4 */
  def protocolVersion: js.UndefOr[ProtocolVersion]
}

object ClientOptions {

  def apply(protocolVersion: js.UndefOr[ProtocolVersion]): ClientOptions =
    ImmutableClientOptions(protocolVersion)

  private class ImmutableClientOptions(val protocolVersion: js.UndefOr[ProtocolVersion])
    extends ClientOptions

}
