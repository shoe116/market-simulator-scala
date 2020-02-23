package com.buffettcode.market.strategy

import com.buffettcode.market.portfolio.Portfolio
import com.buffettcode.market.trade.Order
import org.joda.time.DateTime

trait TradeStrategy {
  def getOrders(portfolio: Portfolio, day: DateTime): Seq[Order]
}
