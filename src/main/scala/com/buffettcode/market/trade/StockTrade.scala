package com.buffettcode.market.trade

import com.buffettcode.market.config.BrokerConfig
import com.buffettcode.market.stock.Stock

case class StockTrade(
  stock: Stock,
  brokerConfig: BrokerConfig.Value,
  count: Int,
  unitPrice: Double
) {
  def totalPrice: Double = count * unitPrice
}
