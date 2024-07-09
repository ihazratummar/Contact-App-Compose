package com.practice.crudoperation2.ui.screen

import android.widget.EditText
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.EditableText
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.practice.crudoperation2.R
import com.practice.crudoperation2.domain.model.Contact
import com.practice.crudoperation2.ui.event.ContactEvent
import com.practice.crudoperation2.ui.event.ContactState
import com.practice.crudoperation2.ui.screen.component.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateContactScreen(
    modifier: Modifier = Modifier,
    state: ContactState,
    event: (ContactEvent) -> Unit,
    navController: NavController,
    contact: Contact
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Edit Contact") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        event(ContactEvent.RefreshContacts)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = {
                            event(ContactEvent.UpdateContact(contact))
                            event(ContactEvent.RefreshContacts)
                            navController.popBackStack()
                        }
                    ) {
                        Text(text = "Save")
                    }
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            item {
                NameWithProfileCard(
                    contact = contact,
                )
            }
            item {
                CustomTextField(
                    label = { Text(text = "First Name") },
                    placeholder = { Text(text = contact.firstName) },
                    value = state.updateFirstname ?: contact.firstName,
                    onValueChange = {
                         state.contact.first().firstName = event(ContactEvent.SetUpdateFirstName(it)).toString()
                    },
                    imeAction = ImeAction.Next
                )
                CustomTextField(
                    label = { Text(text = "Last Name") },
                    placeholder = { Text(text = contact.lastName) },
                    value = state.updateLastname ?: contact.lastName,
                    onValueChange = {
                        event(ContactEvent.SetUpdateLastName(it))
                    },
                    imeAction = ImeAction.Next
                )
                CustomTextField(
                    label = { Text(text = "Phone Number") },
                    placeholder = { Text(text = contact.phoneNumber) },
                    value = state.updatePhoneNumber ?: contact.phoneNumber,
                    onValueChange = {
                        event(ContactEvent.SetUpdatePhoneNumber(it))
                    },
                    keyboardtype = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            }
        }
    }
}


@Composable
private fun NameWithProfileCard(
    contact: Contact,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(200.dp)
                .fillMaxWidth(),
            shape = CircleShape,
            colors = CardDefaults.cardColors(),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.onSurface)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Contact Icon",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}


