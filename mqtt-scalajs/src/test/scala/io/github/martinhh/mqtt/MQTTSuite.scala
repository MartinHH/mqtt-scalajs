package io.github.martinhh.mqtt

import scala.concurrent.ExecutionContext

/**
 * Config of the broker used for testing.
 *
 * Note: the broker is not controlled by theses tests, it must be started independently.
 */
trait TestBrokerConfig {

  protected def wsBrokerPort: Int = 9001

  protected def brokerHost: String = "localhost"

  protected def wsBrokerUrl: String = s"ws://$brokerHost:$wsBrokerPort/"

}

trait MQTTSuite extends munit.FunSuite with TestBrokerConfig {

  protected given ExecutionContext = scala.concurrent.ExecutionContext.global

}
