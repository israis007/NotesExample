package com.israis007.simplenotes.tools

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {

    companion object {
        fun getCalendarFormatted(calendar: Calendar, mask: String): String {
            return try {
                val simpleDateFormat = SimpleDateFormat(mask, Locale.getDefault())
                simpleDateFormat.format(calendar.time)
            } catch (e: Exception) {
                ""
            }
        }
    }
}