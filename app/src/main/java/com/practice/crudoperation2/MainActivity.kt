package com.practice.crudoperation2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.practice.crudoperation2.ui.navigation.NavGraph
import com.practice.crudoperation2.ui.navigation.Route
import com.practice.crudoperation2.ui.screen.ContactScreen
import com.practice.crudoperation2.ui.theme.CrudOperation2Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<ViewModel>()
            CrudOperation2Theme {
                val state by viewModel.state.collectAsState()
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(modifier = Modifier.padding(innerPadding),
                        startDestination = Route.ContactScreen.route,
                        navController = navController,
                        state = state,
                        event = viewModel::onEvent
                    )
                }
            }
        }
    }
}
