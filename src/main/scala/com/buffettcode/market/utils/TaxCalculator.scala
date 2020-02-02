package com.buffettcode.market.utils
import com.buffettcode.market.config.CountryCode
import com.buffettcode.market.errors.UnSupportedCountryException

case class TaxCalculator(countryCode: CountryCode.Value) {
  def calcTax(profit: Double): Double = TaxCalculator.taskRate(countryCode) * profit

}

object TaxCalculator {
  def taskRate(countryCode: CountryCode.Value): Double = countryCode match {
    case CountryCode.JP        => 0.20315
    case CountryCode.TAX_HAVEN => 0.0
    case _                     => throw new UnSupportedCountryException(countryCode)
  }
}
