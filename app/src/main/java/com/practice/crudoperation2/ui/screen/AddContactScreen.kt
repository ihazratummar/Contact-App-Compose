package com.practice.crudoperation2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.practice.crudoperation2.ui.event.ContactEvent
import com.practice.crudoperation2.ui.event.ContactState
import com.practice.crudoperation2.ui.screen.component.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    modifier: Modifier = Modifier,
    onEvent: (ContactEvent) -> Unit,
    state: ContactState,
    navController: NavController
) {
    Scaffold(modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Create New Contact") },
                actions = {
                    IconButton(
                        onClick = {
                            onEvent(ContactEvent.SaveContact)
                            onEvent(ContactEvent.RefreshContacts)
                            navController.popBackStack()
                        },
                        enabled = state.isFormValid
                    ) {
                        Text(text = "Save")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        onEvent(ContactEvent.RefreshContacts)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                label = { Text(text = "First Name") },
                placeholder = { Text(text = "Enter First Name") },
                value = state.firstname,
                onValueChange = {
                    onEvent(ContactEvent.SetFirstname(it))
                },
                imeAction = ImeAction.Next
            )
            CustomTextField(
                label = { Text(text = "Last Name") },
                placeholder = { Text(text = "Enter Last Name") },
                value = state.lastname,
                onValueChange = {
                    onEvent(ContactEvent.SetLastname(it))
                },
                imeAction = ImeAction.Next
            )
            CustomTextField(
                label = { Text(text = "Phone Number") },
                placeholder = { Text(text = "Enter Phone Number") },
                value = state.phoneNumber,
                onValueChange = {
                    onEvent(ContactEvent.SetPhoneNumber(it))
                },
                keyboardtype = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        }
    }
}