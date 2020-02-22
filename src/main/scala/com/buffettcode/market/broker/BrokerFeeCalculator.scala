package com.buffettcode.market.broker

import com.buffettcode.market.config.BrokerConfig

case class BrokerFeeCalculator(config: BrokerConfig.Value) {
  def fee(total: Double): Double = config match {
    case BrokerConfig.FREE => 0.0
    case BrokerConfig.TEST => 10
    case BrokerConfig.RAKUTEN =>
      total match {
        case t if t <= 50000   => 54
        case t if t <= 100000  => 97
        case t if t <= 200000  => 113
        case t if t <= 500000  => 270
        case t if t <= 500000  => 270
        case t if t <= 1000000 => 525
        case t if t <= 1500000 => 628
        case t if t <= 3000000 => 994
        case _                 => 1050
      }
  }
}
