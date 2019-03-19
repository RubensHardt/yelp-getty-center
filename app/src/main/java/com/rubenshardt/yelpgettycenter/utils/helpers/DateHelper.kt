package com.rubenshardt.yelpgettycenter.utils.helpers

import android.content.Context
import com.rubenshardt.yelpgettycenter.R
import org.joda.time.*
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.text.ParseException

object DateHelper {

    private val formatter: DateTimeFormatter? = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

    fun getElapsedTime(context: Context, date: String): String? {
        val dateTime: LocalDateTime?
        try {
            dateTime = formatter?.parseLocalDateTime(date)
        } catch (exception: ParseException) {
            exception.printStackTrace()
            return null
        }
        val dateNow = LocalDateTime.now()
        val years = Years.yearsBetween(dateTime, dateNow).years
        val months = Months.monthsBetween(dateTime, dateNow).months
        val days = Days.daysBetween(dateTime, dateNow).days
        val hours = Hours.hoursBetween(dateTime, dateNow).hours
        return when {
            years > 0 -> context.resources.getQuantityString(R.plurals.years, years, years)
            months > 0 -> context.resources.getQuantityString(R.plurals.months, months, months)
            days > 0 -> context.resources.getQuantityString(R.plurals.days, days, days)
            hours > 0 -> context.resources.getQuantityString(R.plurals.hours, hours, hours)
            else -> context.getString(R.string.now)
        }
    }
}