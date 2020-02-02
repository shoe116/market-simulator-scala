package com.buffettcode.market.errors

import com.buffettcode.market.config.CountryCode

class UnSupportedCountryException(countryCode: CountryCode.Value)
    extends RuntimeException(s"${countryCode} is not Supported.")
class InvalidTradeException(message: String) extends UnsupportedOperationException(message)
