package net.chris.news.fondue.repository.converter

import androidx.room.TypeConverter
import org.joda.time.DateTime

class DateConverter {

    @TypeConverter
    fun fromDate(date: DateTime?): Long = date?.millis ?: DateTime().millis

    @TypeConverter
    fun toDate(timestamp: Long?): DateTime = timestamp?.let { DateTime(timestamp) } ?: DateTime()
}
