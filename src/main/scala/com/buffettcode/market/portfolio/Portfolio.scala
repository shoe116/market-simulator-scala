package com.buffettcode.market.portfolio

import com.buffettcode.market.broker.Broker
import com.buffettcode.market.errors.InvalidTradeException

import scala.collection.mutable.Map
import com.buffettcode.market.stock.{Stock, StockTransaction}

class Portfolio(firstDeposit: Double) {
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

  def buy(transaction: StockTransaction): Portfolio = {
    val broker = Broker(transaction.brokerConfig)
    val cost = broker.calcBuyingCost(transaction.totalPrice)
    // validate
    if (cost > currentDeposit) {
      throw new InvalidTradeException(s"cost=${cost} is over current deposit=${currentDeposit}")
    }

    // update owned stock container
    ownedStocks
      .getOrElse(transaction.stock, OwnedStock(transaction.stock))
      .append(transaction.count, cost)
    currentDeposit -= cost
    totalFee += broker.calcFree(transaction.totalPrice)
    this
  }

  def sell(transaction: StockTransaction): Portfolio = {
    val broker = Broker(transaction.brokerConfig)
    val cost = broker.calcSellingCost(transaction.totalPrice)

    if (cost > currentDeposit + transaction.totalPrice) {
      throw new InvalidTradeException(
        s"cost=${cost} is over current deposit(${currentDeposit}) + trading total price(${transaction.totalPrice})"
      )
    }

    ownedStocks.get(transaction.stock) match {
      case Some(owned) =>
        if (owned.currentCount <= transaction.count) {
          owned.remove(transaction.count)
          this
        } else {
          throw new InvalidTradeException(
            s"owned stock count::${owned.currentCount} is more than selling count::${transaction.count}"
          )
        }
      case _ => throw new InvalidTradeException(s"stock::${transaction.stock} has not owned.")
    }
  }

  def calcTotalAsset(func: OwnedStock => Double) = ???

}
