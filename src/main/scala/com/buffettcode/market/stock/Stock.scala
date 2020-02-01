package com.buffettcode.market.stock
import com.buffettcode.market.config.MarketConfig
import com.buffettcode.market.config.CountryCode

case class Stock(country: CountryCode.Value, market: MarketConfig.Value, ticker: String) {}
