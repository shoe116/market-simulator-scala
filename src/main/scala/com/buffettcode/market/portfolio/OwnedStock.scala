package com.buffettcode.market.portfolio

import com.buffettcode.market.stock.Stock

import scala.math.ceil
import scala.util.Try
import com.buffettcode.market.config.CountryCode
import com.buffettcode.market.errors.{InvalidTradeException, UnSupportedCountryException}

case class OwnedStock(stock: Stock) {
  import com.buffettcode.market.portfolio.OwnedStock._
  var totalCost: Double = 0.0
  var totalCount: Long = 0
  var currentCount: Long = 0
  var averageCost: Double = 0.0

  def append(count: Int, cost: Double): OwnedStock = {
    // overwrite total count if stocks has removed.
    if (totalCount != currentCount) {
      totalCount = currentCount
      totalCost = currentCount * averageCost
    }
    totalCount += count
    totalCost += cost
    currentCount += count
    // calc average Price using Total Average method
    averageCost = calcTotalAverageCost(stock.country, totalCost, totalCount)
    this
  }

  def remove(count: Int): Try[OwnedStock] = Try {
    if (count > currentCount) {
      throw new InvalidTradeException(
        s"count ${count} is more than currentCount ${currentCount}"
      )
    }
    currentCount -= count
    this
  }

}

object OwnedStock {
  def calcTotalAverageCost(
    countryCode: CountryCode.Value,
    totalCost: Double,
    totalCount: Long
  ): Double = countryCode match {
    case CountryCode.JP => ceil(totalCost / totalCount)
    case CountryCode.US => totalCost / totalCount
    case _              => throw new UnSupportedCountryException(countryCode)
  }

}
