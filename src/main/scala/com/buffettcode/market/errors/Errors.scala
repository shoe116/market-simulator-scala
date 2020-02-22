package com.buffettcode.market.errors

import com.buffettcode.market.config.CountryCode
import com.buffettcode.market.trade.OrderPolicy

class UnSupportedCountryException(countryCode: CountryCode.Value)
    extends RuntimeException(s"${countryCode} is not Supported.")
class UnSupportedOrderPolicyException(orderPolicy: OrderPolicy)
    extends RuntimeException(s"${orderPolicy} is not Supported.")
class InvalidTradeException(message: String) extends UnsupportedOperationException(message)
