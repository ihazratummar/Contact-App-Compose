package com.practice.crudoperation2.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.practice.crudoperation2.R
import com.practice.crudoperation2.domain.model.Contact
import com.practice.crudoperation2.ui.event.ContactEvent
import com.practice.crudoperation2.ui.event.ContactState
import com.practice.crudoperation2.ui.navigation.Route
import com.practice.crudoperation2.ui.screen.component.DeleteContactDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContactScreen(
    modifier: Modifier = Modifier,
    state: ContactState,
    event: (ContactEvent) -> Unit,
    navController: NavController,
    contact: Contact
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Contact Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("${Route.UpdateContactScreen.route}/${contact.id}")
                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = {
                        event(ContactEvent.ShowDeleteDialog)
                        event(ContactEvent.RefreshContacts)
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(10.dp)
                .fillMaxSize(),
        ) {
            item {
                NameWithProfileCard(contact)
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = "Contact Info",
                                modifier = Modifier.padding(8.dp),
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = contact.phoneNumber,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }

        }
        Column(
        ) {

        }
        if (state.isDeletingContact) {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                DeleteContactDialog(
                    onDismiss = {
                        event(ContactEvent.HideDeleteDialog)
                    },
                    onConfirm = {
                        event(ContactEvent.DeleteContact(contact))
                        event(ContactEvent.RefreshContacts)
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
private fun NameWithProfileCard(
    contact: Contact
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
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Contact Icon",
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${contact.firstName} ${contact.lastName}",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}
