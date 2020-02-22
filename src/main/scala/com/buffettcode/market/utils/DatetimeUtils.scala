package com.buffettcode.market.utils

import java.time._
import java.time.format.DateTimeFormatter
import java.util.Locale

object DatetimeUtils {
  var YYYYMMDD_PATTERN = "yyyyMMdd"
  var YYYY_MM_DD_PATTERN = "yyyy-MM-dd"
  val UTC_TIMEZONE = ZoneId.of("UTC")
  val TOKYO_TIMEZONE = ZoneId.of("Asia/Tokyo")
  val NEW_YORK_TIMEZONE = ZoneId.of("America/New_York")

  def getEpochTimestampMs(datetimeStr: String, pattern: String, locale: Locale): Long =
    OffsetDateTime
      .from(DateTimeFormatter.ofPattern(pattern).withLocale(locale).parse(datetimeStr))
      .toInstant
      .toEpochMilli

}
