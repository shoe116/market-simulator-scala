package com.buffettcode.market.trade

sealed trait OrderPolicy

sealed trait BuyingOrderPolicy extends OrderPolicy
sealed trait SellingOrderPolicy extends OrderPolicy

case class BuyingMarketOrderAsPossiblePolicy(deposit: Double) extends BuyingOrderPolicy
case class BuyingMarketOrderMoreThanPolicy(underLimit: Int) extends BuyingOrderPolicy
case class SellingMarketOrderPolicy(count: Int) extends SellingOrderPolicy
