package com.practice.crudoperation2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.practice.crudoperation2.data.dao.ContactDao
import com.practice.crudoperation2.domain.model.Contact

@Database(entities = [Contact::class], version = 3)
@TypeConverters(Converts::class)
abstract class ContactDatabase : RoomDatabase(){
    abstract fun dao(): ContactDao
}