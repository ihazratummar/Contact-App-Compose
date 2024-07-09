package com.practice.crudoperation2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practice.crudoperation2.ui.event.ContactEvent
import com.practice.crudoperation2.ui.event.ContactState
import com.practice.crudoperation2.ui.screen.ContactScreen
import com.practice.crudoperation2.ui.screen.AddContactScreen
import com.practice.crudoperation2.ui.screen.DetailContactScreen
import com.practice.crudoperation2.ui.screen.UpdateContactScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    state: ContactState,
    event: (ContactEvent) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Route.ContactScreen.route) {
            ContactScreen(
                modifier = modifier,
                state =state,
                event = event,
                navController = navController
            )
        }
        composable(route = Route.AddContactScreen.route) {
            AddContactScreen(
                modifier = modifier,
                state =state,
                onEvent = event,
                navController = navController
            )
        }
        composable(route = Route.DetailContactScreen.route + "/{contactId}") {  backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")
            val contact = state.contact.find { it.id.toString() == contactId }
            DetailContactScreen(
                modifier = modifier,
                state = state,
                event = event,
                navController = navController,
                contact = contact ?: throw IllegalStateException("Contact not found")
            )
        }
        composable(route = Route.UpdateContactScreen.route + "/{contactId}") {  backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")
            val contact = state.contact.find { it.id.toString() == contactId }
            UpdateContactScreen(
                modifier = modifier,
                state = state,
                event = event,
                navController = navController,
                contact = contact ?: throw IllegalStateException("Contact not found")
            )
        }
    }
}