package com.evanstukalov.fakekinopoiskapp.database

import androidx.room.TypeConverter

class MyConverters {

    @TypeConverter
    fun fromListToString(value: List<String>): String{
        return value.joinToString(",")
    }
    @TypeConverter
    fun fromStringToList(value: String): List<String>{
        return value.split(",")
    }

}