package com.onemonster.movienotes.room

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun intListToString(list: List<Int>): String {
        return list.joinToString(",")
    }

    @TypeConverter
    fun stringToIntList(value: String): List<Int> {
        return value.split(",").map { it.toInt() }
    }
}