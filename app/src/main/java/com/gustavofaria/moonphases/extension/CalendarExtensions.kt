package com.gustavofaria.moonphases.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Calendar.format(pattern: String): String? {
    return try {
        val dateFormat = SimpleDateFormat(pattern, Locale("pt", "br"))
        val string = dateFormat.format(this.time)
        string
    } catch (e: ParseException) {
        null
    }
}
