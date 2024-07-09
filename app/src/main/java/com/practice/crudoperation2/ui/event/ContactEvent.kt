package com.practice.crudoperation2.ui.event

import com.practice.crudoperation2.domain.model.Contact

sealed interface ContactEvent {

    //Add Contact
    object SaveContact : ContactEvent
    data class SetFirstname(val firstname: String) : ContactEvent
    data class SetLastname(val lastname: String) : ContactEvent
    data class SetPhoneNumber(val phoneNumber: String) : ContactEvent

    //Update Contact
    data class SetUpdateFirstName(val updateFirstName: String?) : ContactEvent
    data class SetUpdateLastName(val updateLastName: String?) : ContactEvent
    data class SetUpdatePhoneNumber(val updatePhoneNumber: String?) : ContactEvent
    data class UpdateContact(val contact: Contact): ContactEvent

    //Delete Contact
    data class DeleteContact(val contact: Contact): ContactEvent

    //Dialogs
    object ShowDialog : ContactEvent
    object HideDialog : ContactEvent
    object ShowDeleteDialog: ContactEvent
    object HideDeleteDialog: ContactEvent


    object RefreshContacts : ContactEvent
}
