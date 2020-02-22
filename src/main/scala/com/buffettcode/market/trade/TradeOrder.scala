package com.buffettcode.market.trade

import com.buffettcode.market.config.BrokerConfig
import com.buffettcode.market.stock.Stock
import org.joda.time.DateTime

sealed trait Order {
  def trade(getStockPrice: (DateTime, Stock) => Double): StockTrade
}

case class BuyingOrder(
  day: DateTime,
  stock: Stock,
  brokerConfig: BrokerConfig.Value,
  policy: BuyingOrderPolicy
) extends Order {
  override def trade(getStockPrice: (DateTime, Stock) => Double): StockTrade =
    policy match {
      case p: BuyingMarketOrderAsPossiblePolicy => {
        val price = getStockPrice(day, stock)
        // limit
        val units: Int = (p.deposit / price / stock.unitNumber).ceil.toInt
        StockTrade(stock, brokerConfig, units * stock.unitNumber, price)
      }
      case p: BuyingMarketOrderMoreThanPolicy => {
        val price = getStockPrice(day, stock)
        val unitPrice = stock.unitNumber * price
        val counts: Int = if (unitPrice > p.underLimit) {
          stock.unitNumber
        } else {
          (unitPrice / p.underLimit).ceil.toInt
        }
        StockTrade(stock, brokerConfig, counts, price)
      }
    }
}

case class SellingOrder(
  day: DateTime,
  stock: Stock,
  brokerConfig: BrokerConfig.Value,
  count: Int,
  policy: SellingOrderPolicy
) extends Order {
  override def trade(getStockPrice: (DateTime, Stock) => Double): StockTrade = policy match {
    case _: SellingMarketOrderPolicy =>
      StockTrade(stock, brokerConfig, count, getStockPrice(day, stock))
  }
}
