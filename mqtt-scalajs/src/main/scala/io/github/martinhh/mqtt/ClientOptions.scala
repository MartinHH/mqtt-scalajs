package io.github.martinhh.mqtt

import io.github.martinhh.mqtt.ClientOptions.*
import io.github.martinhh.mqtt.buffer.Buffer

import scala.scalajs.js

/**
 * Base trait for the client (connect) options object.
 *
 * mqtt.js is not transparent about which members it expects to be mutable, so the only
 * implementation of this is `MutableClientOptions` which is fully mutable.
 */
sealed trait ClientOptions extends js.Object {
  def auth: js.UndefOr[String]

  // TODO
  // def authPacket: js.UndefOr[PartialAuthPacket]

  def autoAssignTopicAlias: js.UndefOr[Boolean]

  def autoUseTopicAlias: js.UndefOr[Boolean]

  def binary: js.UndefOr[Boolean]

  /** in Bytes, default is 512 * 1024 */
  def browserBufferSize: js.UndefOr[Int]

  /**
   * When using websockets, this is the timeout used when writing to socket (in ms).
   * Default is 1000.
   */
  def browserBufferTimeout: js.UndefOr[Int]

  def clean: js.UndefOr[Boolean]

  def clientId: js.UndefOr[String]

  /** In ms, default is 30 * 1000 . */
  def connectTimeout: js.UndefOr[Int]

  def createWebsocket: js.UndefOr[WebsocketFactory]

  // TODO
  // def customHandleAcks: js.UndefOr[AckHandler]

  def defaultProtocol: js.UndefOr[MqttProtocol]

  def encoding: js.UndefOr[Buffer.Encoding]

  def host: js.UndefOr[String]

  // TODO
  // def incomingStore: js.UndefOr[Store]

  /** In s, default is 60. */
  def keepalive: js.UndefOr[Int]

  def log: js.UndefOr[LogFunction]

  def manualConnect: js.UndefOr[Boolean]

  // TODO
  // def messageIdProvider: js.UndefOr[MessageIdProvider]

  /** Used on ali protocol */
  def my: js.UndefOr[Any]

  def objectMode: js.UndefOr[Boolean]

  // TODO
  // def outgoingStore: js.UndefOr[Store]

  def password: js.UndefOr[Buffer | String]

  def path: js.UndefOr[String]

  /** Broker port */
  def port: js.UndefOr[Int]

  // TODO
  // def properties: js.UndefOr[AuthenticationMethod]

  def protocol: js.UndefOr[MqttProtocol]

  def protocolId: js.UndefOr[ProtocolId]

  /** Defaults to 4 */
  def protocolVersion: js.UndefOr[ProtocolVersion]

  def query: js.UndefOr[js.Dictionary[String]]

  def queueQoSZero: js.UndefOr[Boolean]

  /** Interval between two reconnections in ms, default is 1000. */
  def reconnectPeriod: js.UndefOr[Int]

  def reschedulePings: js.UndefOr[Boolean]

  def resubscribe: js.UndefOr[Boolean]

  def servername: js.UndefOr[String]

  // TODO:
  // def servers: js.UndefOr[js.Array[Host]]

  def timerVariant: js.UndefOr[TimerVariant]

  def transformWsUrl: js.UndefOr[WsUrlTransformation]

  def username: js.UndefOr[String]

  // TODO
  // def will: js.UndefOr[Payload]

  def writeCache: js.UndefOr[Boolean]

  def wsOptions: js.UndefOr[ClientOptions /* TODO | ClientRequestArgs | DuplexOptions*/ ]
}

object ClientOptions {

  trait LogFunction extends js.Function {
    def apply(args: Any*): Unit
  }

  type MqttProtocol =
    "ali" | "alis" | "mqtt" | "mqtts" | "ssl" | "tcp" | "ws" | "wss" | "wx" | "wxs"

  type ProtocolId = "MQTT" | "MQIsdp"

  type TimerVariant = "auto" | "native" | "worker"

  type UserProperties = js.Dictionary[String | js.Array[String]]

  type WebsocketFactory = js.Function3[String, js.Array[String], ClientOptions, Any]

  type WsUrlTransformation = js.Function3[String, ClientOptions, MqttClient, String]

  // TODO (?): should there really be a factory like this?
  def apply(
    auth: js.UndefOr[String] = js.undefined,
    autoAssignTopicAlias: js.UndefOr[Boolean] = js.undefined,
    autoUseTopicAlias: js.UndefOr[Boolean] = js.undefined,
    binary: js.UndefOr[Boolean] = js.undefined,
    browserBufferSize: js.UndefOr[Int] = js.undefined,
    browserBufferTimeout: js.UndefOr[Int] = js.undefined,
    clean: js.UndefOr[Boolean] = js.undefined,
    clientId: js.UndefOr[String] = js.undefined,
    connectTimeout: js.UndefOr[Int] = js.undefined,
    createWebsocket: js.UndefOr[WebsocketFactory] = js.undefined,
    defaultProtocol: js.UndefOr[MqttProtocol] = js.undefined,
    encoding: js.UndefOr[Buffer.Encoding] = js.undefined,
    host: js.UndefOr[String] = js.undefined,
    keepalive: js.UndefOr[Int] = js.undefined,
    log: js.UndefOr[LogFunction] = js.undefined,
    manualConnect: js.UndefOr[Boolean] = js.undefined,
    my: js.UndefOr[Any] = js.undefined,
    objectMode: js.UndefOr[Boolean] = js.undefined,
    password: js.UndefOr[Buffer | String] = js.undefined,
    path: js.UndefOr[String] = js.undefined,
    port: js.UndefOr[Int] = js.undefined,
    protocol: js.UndefOr[MqttProtocol] = js.undefined,
    protocolId: js.UndefOr[ProtocolId] = js.undefined,
    protocolVersion: js.UndefOr[ProtocolVersion] = js.undefined,
    query: js.UndefOr[js.Dictionary[String]] = js.undefined,
    queueQoSZero: js.UndefOr[Boolean] = js.undefined,
    reconnectPeriod: js.UndefOr[Int] = js.undefined,
    reschedulePings: js.UndefOr[Boolean] = js.undefined,
    resubscribe: js.UndefOr[Boolean] = js.undefined,
    servername: js.UndefOr[String] = js.undefined,
    timerVariant: js.UndefOr[TimerVariant] = js.undefined,
    transformWsUrl: js.UndefOr[WsUrlTransformation] = js.undefined,
    username: js.UndefOr[String] = js.undefined,
    writeCache: js.UndefOr[Boolean] = js.undefined,
    wsOptions: js.UndefOr[ClientOptions] = js.undefined
  ): ClientOptions =
    val opts = new MutableClientOptions {}
    clientId.foreach(_ => opts.clientId = clientId)
    connectTimeout.foreach(_ => opts.connectTimeout = connectTimeout)
    createWebsocket.foreach(_ => opts.createWebsocket = createWebsocket)
    defaultProtocol.foreach(_ => opts.defaultProtocol = defaultProtocol)
    encoding.foreach(_ => opts.encoding = encoding)
    host.foreach(_ => opts.host = host)
    keepalive.foreach(_ => opts.keepalive = keepalive)
    log.foreach(_ => opts.log = log)
    manualConnect.foreach(_ => opts.manualConnect = manualConnect)
    my.foreach(_ => opts.my = my)
    objectMode.foreach(_ => opts.objectMode = objectMode)
    password.foreach(_ => opts.password = password)
    path.foreach(_ => opts.path = path)
    port.foreach(_ => opts.port = port)
    protocol.foreach(_ => opts.protocol = protocol)
    protocolId.foreach(_ => opts.protocolId = protocolId)
    protocolVersion.foreach(_ => opts.protocolVersion = protocolVersion)
    query.foreach(_ => opts.query = query)
    queueQoSZero.foreach(_ => opts.queueQoSZero = queueQoSZero)
    reconnectPeriod.foreach(_ => opts.reconnectPeriod = reconnectPeriod)
    reschedulePings.foreach(_ => opts.reschedulePings = reschedulePings)
    resubscribe.foreach(_ => opts.resubscribe = resubscribe)
    servername.foreach(_ => opts.servername = servername)
    timerVariant.foreach(_ => opts.timerVariant = timerVariant)
    transformWsUrl.foreach(_ => opts.transformWsUrl = transformWsUrl)
    username.foreach(_ => opts.username = username)
    writeCache.foreach(_ => opts.writeCache = writeCache)
    wsOptions.foreach(_ => opts.wsOptions = wsOptions)
    opts
}

trait MutableClientOptions extends ClientOptions {

  var auth: js.UndefOr[String] = js.undefined

  var autoAssignTopicAlias: js.UndefOr[Boolean] = js.undefined

  var autoUseTopicAlias: js.UndefOr[Boolean] = js.undefined

  var binary: js.UndefOr[Boolean] = js.undefined

  var browserBufferSize: js.UndefOr[Int] = js.undefined

  var browserBufferTimeout: js.UndefOr[Int] = js.undefined

  var clean: js.UndefOr[Boolean] = js.undefined

  var clientId: js.UndefOr[String] = js.undefined

  var connectTimeout: js.UndefOr[Int] = js.undefined

  var createWebsocket: js.UndefOr[WebsocketFactory] = js.undefined

  var defaultProtocol: js.UndefOr[MqttProtocol] = js.undefined

  var encoding: js.UndefOr[Buffer.Encoding] = js.undefined

  var host: js.UndefOr[String] = js.undefined

  var keepalive: js.UndefOr[Int] = js.undefined

  var log: js.UndefOr[LogFunction] = js.undefined

  var manualConnect: js.UndefOr[Boolean] = js.undefined

  var my: js.UndefOr[Any] = js.undefined

  var objectMode: js.UndefOr[Boolean] = js.undefined

  var password: js.UndefOr[Buffer | String] = js.undefined

  var path: js.UndefOr[String] = js.undefined

  var port: js.UndefOr[Int] = js.undefined

  var protocol: js.UndefOr[MqttProtocol] = js.undefined

  var protocolId: js.UndefOr[ProtocolId] = js.undefined

  var protocolVersion: js.UndefOr[ProtocolVersion] = js.undefined

  var query: js.UndefOr[js.Dictionary[String]] = js.undefined

  var queueQoSZero: js.UndefOr[Boolean] = js.undefined

  var reconnectPeriod: js.UndefOr[Int] = js.undefined

  var reschedulePings: js.UndefOr[Boolean] = js.undefined

  var resubscribe: js.UndefOr[Boolean] = js.undefined

  var servername: js.UndefOr[String] = js.undefined

  var timerVariant: js.UndefOr[TimerVariant] = js.undefined

  var transformWsUrl: js.UndefOr[WsUrlTransformation] = js.undefined

  var username: js.UndefOr[String] = js.undefined

  var writeCache: js.UndefOr[Boolean] = js.undefined

  var wsOptions: js.UndefOr[ClientOptions] = js.undefined
}
