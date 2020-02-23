package com.buffettcode.market.stock

import com.buffettcode.market.config.MarketConfig
import org.joda.time.DateTime
import scala.collection.mutable.Map


case class DailyStockPrices(day: DateTime, market: MarketConfig.Value) {
  val container = Map.empty[Stock, Double]

  def put(stock: Stock, price: Double): DailyStockPrices = {
    if (stock.market != market) {
      throw new RuntimeException(s"${stock} is not in ${market}. something wrong.")
    }
    container.put(stock, price)
    this
  }

  def getPrice(stock: Stock): Option[Double] = container.get(stock)

}
