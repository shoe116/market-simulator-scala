package com.buffettcode.market.simulator
import com.buffettcode.market.stock.DailyStockPrices
import com.buffettcode.market.strategy.TradeStrategy
import org.joda.time.DateTime

import scala.collection.mutable.ListBuffer

case class MarketSimulator(startDate: DateTime, endDate: DateTime, strategy: TradeStrategy) {
  val dailyStockPrices = ListBuffer[DailyStockPrices]()
}
