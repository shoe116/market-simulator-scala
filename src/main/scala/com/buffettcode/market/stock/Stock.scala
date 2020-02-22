package com.buffettcode.market.stock
import com.buffettcode.market.config.MarketConfig

case class Stock(market: MarketConfig.Value, ticker: String, unitNumber: Int) {}

object Stock {
  val DEFAULT_UNIT_NUMBER: Int = 100
  def create(market: MarketConfig.Value, ticker: String, unitNumber: Int) =
    Stock(market, ticker, unitNumber)
  def create(market: MarketConfig.Value, ticker: String) =
    Stock(market, ticker, DEFAULT_UNIT_NUMBER)
}
