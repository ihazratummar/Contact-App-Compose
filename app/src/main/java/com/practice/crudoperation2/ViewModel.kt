package com.practice.crudoperation2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.crudoperation2.domain.model.Contact
import com.practice.crudoperation2.domain.repository.ContactRepository
import com.practice.crudoperation2.ui.event.ContactEvent
import com.practice.crudoperation2.ui.event.ContactState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    private val _contact = MutableStateFlow<List<Contact>>(emptyList())
    val contact = _contact.asStateFlow()

    private val _state = MutableStateFlow(ContactState())

    val state = combine(_state, _contact) { state, contact ->
        state.copy(
            contact = contact
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    init {
        refreshContacts()
    }

    private fun isFormValid(): Boolean {
        val first = _state.value.firstname
        val phone = _state.value.phoneNumber
        val isPhoneValid = phone.length >= 10
        val isFormValid = first.isNotEmpty() && phone.isNotEmpty() && isPhoneValid
        _state.update {
            it.copy(
                isFormValid = isFormValid
            )
        }
        return isFormValid
    }


    fun onEvent(event: ContactEvent) {
        when (event) {

            ContactEvent.SaveContact -> {
                val first = _state.value.firstname
                val last = _state.value.lastname
                val phone = _state.value.phoneNumber

                if (first.isBlank() || phone.isBlank()) {
                    return
                }
                val contact = Contact(
                    firstName = first,
                    lastName = last,
                    phoneNumber = phone
                )
                viewModelScope.launch {
                    repository.insertContact(contact)
                    refreshContacts()
                }
                _state.update {
                    it.copy(
                        isAddingContact = false,
                        firstname = "",
                        lastname = "",
                        phoneNumber = ""
                    )
                }
            }

            is ContactEvent.UpdateContact -> {
                val first = _state.value.updateFirstname?: event.contact.firstName
                val last = _state.value.updateLastname?:event.contact.lastName
                val phone = _state.value.updatePhoneNumber?:event.contact.phoneNumber

                val contact = Contact(
                    id = event.contact.id,
                    firstName = first,
                    lastName = last,
                    phoneNumber = phone,
                )
                viewModelScope.launch {
                    repository.updateContact(contact)
                    refreshContacts()
                }
                _state.update {
                    it.copy(
                        isUpdatingContact = false,
                        updateFirstname = null,
                        updateLastname = null,
                        updatePhoneNumber = null,
                    )
                }
            }

            is ContactEvent.SetFirstname -> {
                _state.update {
                    it.copy(
                        firstname = event.firstname
                    )
                }
                isFormValid()
            }

            is ContactEvent.SetLastname -> {
                _state.update {
                    it.copy(
                        lastname = event.lastname
                    )
                }
                isFormValid()
            }

            is ContactEvent.SetPhoneNumber -> {
                _state.update {
                    it.copy(
                        phoneNumber = event.phoneNumber
                    )
                }
                isFormValid()
            }

            is ContactEvent.SetUpdateLastName -> {
                _state.update {
                    it.copy(
                        updateLastname = event.updateLastName?: contact.value.first().lastName
                    )
                }
            }

            is ContactEvent.SetUpdateFirstName -> {
                _state.update {
                    it.copy(
                        updateFirstname = event.updateFirstName
                    )
                }
            }

            is ContactEvent.SetUpdatePhoneNumber -> {
                _state.update {
                    it.copy(
                        updatePhoneNumber = event.updatePhoneNumber?: contact.value.first().phoneNumber
                    )
                }
            }

            is ContactEvent.DeleteContact -> {
                viewModelScope.launch(Dispatchers.IO) {
                    repository.deleteContact(event.contact)
                    val updatedContacts = repository.getAllContacts()
                    _contact.value = updatedContacts
                    _state.update {
                        it.copy(
                            isDeletingContact = false
                        )
                    }
                }
            }

            ContactEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = false
                    )
                }
            }

            ContactEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = true
                    )
                }
            }

            ContactEvent.RefreshContacts -> {
                refreshContacts()
            }

            ContactEvent.HideDeleteDialog -> {
                _state.update {
                    it.copy(
                        isDeletingContact = false
                    )
                }
            }

            ContactEvent.ShowDeleteDialog -> {
                _state.update {
                    it.copy(
                        isDeletingContact = true
                    )
                }
            }

        }
    }

    private fun refreshContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val contacts = repository.getAllContacts()
            _contact.value = contacts
            _state.update {
                it.copy(
                    contact = contacts,
                    isUpdatingContact = false,
                    isDeletingContact = false,
                    isAddingContact = false
                )
            }
        }
    }
}