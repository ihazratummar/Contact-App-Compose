package com.practice.crudoperation2.data.repository

import com.practice.crudoperation2.data.dao.ContactDao
import com.practice.crudoperation2.domain.model.Contact
import com.practice.crudoperation2.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow

class ContactRepositoryImpl (
    private val dao: ContactDao
): ContactRepository {
    override suspend fun insertContact(contact: Contact) {
        dao.insertContact(contact)
    }

    override suspend fun updateContact(contact: Contact) {
        dao.updateContact(contact)
    }

    override suspend fun deleteContact(contact: Contact) {
        dao.deleteContact(contact)
    }

    override fun getAllContacts(): List<Contact> {
        return dao.getAllContact()
    }
}