package com.buffettcode.market.portfolio

import scala.collection.mutable.Map
import com.buffettcode.market.stock.Stock

class Portfolio(firstDeposit: Double) {
  // init values
  var currentDeposit: Double = firstDeposit
  var totalInvestment: Double = firstDeposit
  var totalProfit: Double = 0.0
  var totalTax = 0.0
  var totalFee = 0.0
  val ownedStockContainer = Map.empty[Stock, OwnedStock]

  def deposit(amount: Double): Portfolio = {
    currentDeposit += amount
    totalInvestment += amount
    this
  }

  def buy(stock: Stock, count: Int, unitPrice: Double): Portfolio = {
    this
  }

}
