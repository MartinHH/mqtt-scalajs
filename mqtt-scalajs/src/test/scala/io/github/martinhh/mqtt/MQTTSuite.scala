package io.github.martinhh.mqtt

import scala.concurrent.ExecutionContext

/**
 * Config of the broker used for testing.
 *
 * Note: the broker is not controlled by theses tests, it must be started independently.
 */
trait TestBrokerConfig {

  private def getEnvVar(key: String): Option[String] = {
    val env = scala.scalajs.js.Dynamic.global.process.env
    env.selectDynamic(key).asInstanceOf[scala.scalajs.js.UndefOr[String]].toOption
  }

  protected def brokerHostFromEnv: Option[String] = getEnvVar("TEST_BROKER_HOST")

  protected def wsBrokerPort: Int = 8083

  protected def brokerHost: String = brokerHostFromEnv.getOrElse("localhost")

  protected def wsBrokerUrl: String = s"ws://$brokerHost:$wsBrokerPort/"

}

trait MQTTSuite extends munit.FunSuite with TestBrokerConfig {

  protected given ExecutionContext = scala.concurrent.ExecutionContext.global

}
