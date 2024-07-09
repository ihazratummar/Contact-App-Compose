package com.practice.crudoperation2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.practice.crudoperation2.domain.model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY first_name ASC")
    fun getContactOrderByFirstName(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY last_name ASC")
    fun getContactOrderByLastName(): Flow<List<Contact>>

    @Query("SELECT * FROM contact ORDER BY phone_number ASC")
    fun getContactOrderByPhoneNumber(): Flow<List<Contact>>

    @Query("SELECT * FROM contact")
    fun getAllContact(): List<Contact>

}