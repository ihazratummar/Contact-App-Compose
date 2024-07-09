package com.practice.crudoperation2.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.practice.crudoperation2.ui.event.ContactEvent
import com.practice.crudoperation2.ui.event.ContactState
import com.practice.crudoperation2.ui.navigation.Route
import com.practice.crudoperation2.ui.screen.component.ContactCard
import com.practice.crudoperation2.ui.screen.component.EmptyScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreen(
    modifier: Modifier,
    state: ContactState,
    event: (ContactEvent) -> Unit,
    navController: NavController
) {

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Contacts")})
    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Route.AddContactScreen.route) }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->

        if (state.isAddingContact) {
            AddContactScreen(
                onEvent = event,
                state = state,
                navController = navController
            )
        }
        if (state.contact.isEmpty()) {
            EmptyScreen()
        }else{
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 10.dp)
            ) {
                items(state.contact) { contact ->
                    ContactCard(
                        contact = contact,
                        onEvent = event,
                        navController = navController
                    )
                }
            }
        }
    }
}
