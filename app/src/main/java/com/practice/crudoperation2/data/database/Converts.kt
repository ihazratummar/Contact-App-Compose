package com.practice.crudoperation2.data.database

import android.util.Base64
import androidx.room.TypeConverter

class Converts {
    @TypeConverter
    fun fromByteArray(bytes: ByteArray?): String? {
        return bytes?.let { Base64.encodeToString(it, Base64.DEFAULT)}
    }

    fun toByteArray(encodedString: String?): ByteArray? {
        return encodedString?.let { Base64.decode(it, Base64.DEFAULT) }
    }
}