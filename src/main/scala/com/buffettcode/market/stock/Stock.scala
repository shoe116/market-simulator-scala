package com.buffettcode.market.stock
import com.buffettcode.market.config.MarketConfig
import com.buffettcode.market.config.CountryCode
import com.buffettcode.market.config.BrokerConfig

case class Stock(country: CountryCode.Value, market: MarketConfig.Value, ticker: String) {}

case class StockTransaction(
  stock: Stock,
  brokerConfig: BrokerConfig.Value,
  count: Int,
  unitPrice: Double
) {
  def totalPrice: Double = count * unitPrice
}
