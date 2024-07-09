package com.practice.crudoperation2.domain.repository

import com.practice.crudoperation2.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    suspend fun insertContact(contact: Contact)
    suspend fun updateContact(contact: Contact)
    suspend fun deleteContact(contact: Contact)

    fun getAllContacts(): List<Contact>

}