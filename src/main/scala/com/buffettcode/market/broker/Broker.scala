package com.buffettcode.market.broker
import com.buffettcode.market.config.BrokerConfig

case class Broker(config: BrokerConfig.Value) {
  def calcFree(totalPrice: Double) = BrokerFeeCalculator(config).fee(totalPrice)

  def calcBuyingCost(totalPrice: Double) = {
    totalPrice + calcFree(totalPrice)
  }

  def calcSellingCost(totalPrice: Double) = calcFree(totalPrice)
}
