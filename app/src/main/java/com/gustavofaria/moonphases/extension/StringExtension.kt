package com.gustavofaria.moonphases.extension

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun String.setBold(vararg boldSubstrings: String): AnnotatedString {
    val lowerString = this.lowercase()
    return buildAnnotatedString {
        append(this@setBold)
        boldSubstrings.forEach { boldSubstring ->
            val lowerBoldString = boldSubstring.lowercase()
            val startIndex = lowerString.indexOf(lowerBoldString)
            val endIndex = startIndex + lowerBoldString.length
            addStyle(
                style = SpanStyle(fontWeight = FontWeight.ExtraBold),
                start = startIndex,
                end = endIndex
            )
        }
    }
}

fun String.toCalendar(pattern: String): Calendar? {
    return try {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat(pattern, Locale("pt", "br"))
        calendar.time = dateFormat.parse(this) as Date
        calendar
    } catch (e: ParseException) {
        null
    }
}
