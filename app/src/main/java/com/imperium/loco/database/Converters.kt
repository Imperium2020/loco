package com.imperium.loco.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class Converters {

    private val gson = Gson()

    // Date
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    // List<PantryItem>
    @TypeConverter
    fun pantryItemListToJson(list: List<PantryItem>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun jsonToPantryItemList(jsonList: String): List<PantryItem> {
        return gson.fromJson(jsonList, object : TypeToken<List<PantryItem>>() {}.type)
    }

    // List<String>
    @TypeConverter
    fun listStringToJson(list: List<String>?): String? {
        return gson.toJson(list)?.toString()
    }

    @TypeConverter
    fun jsonToListString(jsonList: String?): List<String>? {
        return gson.fromJson(jsonList, object : TypeToken<List<String>?>() {}.type)
    }
}