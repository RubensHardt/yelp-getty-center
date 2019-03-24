package com.rubenshardt.yelpgettycenter.utils.helpers

import android.content.Context
import com.rubenshardt.yelpgettycenter.R
import org.joda.time.*
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


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
        val elapsedTime = when {
            years > 0 -> context.resources.getQuantityString(R.plurals.years, years, years)
            months > 0 -> context.resources.getQuantityString(R.plurals.months, months, months)
            days > 0 -> context.resources.getQuantityString(R.plurals.days, days, days)
            hours > 0 -> context.resources.getQuantityString(R.plurals.hours, hours, hours)
            else -> null
        }
        return elapsedTime?.let {context.getString(R.string.time_ago, it)} ?: context.getString(R.string.now)
    }

    fun getUserFriendlyTime(time: String?): String? {
        val dateFormat = SimpleDateFormat("HHmm", Locale.US)
        val userFriendlyFormat = SimpleDateFormat("h:mm a", Locale.US)
        return try {
            val dateObj = dateFormat.parse(time)
            userFriendlyFormat.format(dateObj)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }

    }
}