package com.practice.crudoperation2.ui.event

import com.practice.crudoperation2.domain.model.Contact

data class ContactState(
    var contact: List<Contact> = emptyList(),
    val updateContact: Contact? = null,
    val firstname: String = "",
    val lastname: String = "",
    val phoneNumber: String = "",
    val updateFirstname: String ? = null,
    val updateLastname: String? = null,
    val updatePhoneNumber: String? = null,
    val isAddingContact: Boolean = false,
    val isFormValid: Boolean = false,
    val isUpdateFormValid: Boolean = false,
    val isDeletingContact: Boolean = false,
    val isUpdatingContact: Boolean = false,
)