package com.practice.crudoperation2.ui.navigation

sealed class Route(val route: String){
    object ContactScreen: Route("contact_screen")
    object AddContactScreen: Route("add_contact_screen")
    object UpdateContactScreen: Route("update_contact_screen")
    object DetailContactScreen: Route("detail_contact_screen/{contactId}")
}