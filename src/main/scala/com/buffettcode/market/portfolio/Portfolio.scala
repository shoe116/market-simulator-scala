package com.buffettcode.market.portfolio

import com.buffettcode.market.broker.Broker
import com.buffettcode.market.config.CountryCode
import com.buffettcode.market.errors.InvalidTradeException

import scala.collection.mutable.Map
import com.buffettcode.market.stock.Stock
import com.buffettcode.market.trade.StockTrade

class Portfolio(countryCode: CountryCode.Value, firstDeposit: Double) {
  // init values
  var currentDeposit: Double = firstDeposit
  var totalInvestment: Double = firstDeposit
  var totalProfit: Double = 0.0
  var totalTax = 0.0
  var totalFee = 0.0
  val ownedStocks = Map.empty[Stock, OwnedStock]

  def deposit(amount: Double): Portfolio = {
    currentDeposit += amount
    totalInvestment += amount
    this
  }

  def buy(stockTrade: StockTrade): Portfolio = {
    val broker = Broker(stockTrade.brokerConfig)
    val cost = broker.calcBuyingCost(stockTrade.totalPrice)
    // validate
    if (cost > currentDeposit) {
      throw new InvalidTradeException(s"cost=${cost} is over current deposit=${currentDeposit}")
    }

    // update owned stock container
    ownedStocks
      .getOrElse(stockTrade.stock, OwnedStock(countryCode, stockTrade.stock))
      .append(stockTrade.count, cost)
    currentDeposit -= cost
    totalFee += broker.calcFree(stockTrade.totalPrice)
    this
  }

  def sell(stockTrade: StockTrade): Portfolio = {
    val broker = Broker(stockTrade.brokerConfig)
    val cost = broker.calcSellingCost(stockTrade.totalPrice)

    if (cost > currentDeposit + stockTrade.totalPrice) {
      throw new InvalidTradeException(
        s"cost=${cost} is over current deposit(${currentDeposit}) + trading total price(${stockTrade.totalPrice})"
      )
    }

    ownedStocks.get(stockTrade.stock) match {
      case Some(owned) =>
        if (owned.currentCount <= stockTrade.count) {
          owned.remove(stockTrade.count)
          this
        } else {
          throw new InvalidTradeException(
            s"owned stock count::${owned.currentCount} is more than selling count::${stockTrade.count}"
          )
        }
      case _ => throw new InvalidTradeException(s"stock::${stockTrade.stock} has not owned.")
    }
  }

  def calcTotalAsset(getCurrentPriceFunc: OwnedStock => Double): Double =
    ownedStocks.values.map(v => getCurrentPriceFunc(v)).sum + currentDeposit

}
