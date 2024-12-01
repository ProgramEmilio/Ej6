package com.example.ej6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ej6.viewmodels.BookViewModel
import com.example.ej6.views.AddBookView
import com.example.ej6.views.EditBookView
import com.example.ej6.views.HomeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}


@Composable
fun AppNavigation(viewModel: BookViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeView(
                viewModel,
                onNavigateToAdd = { navController.navigate("add") },
                onNavigateToEdit = { id -> navController.navigate("edit/$id") }
            )
        }
        composable("add") {
            AddBookView(viewModel, onNavigateBack = { navController.popBackStack() })
        }
        composable(
            route = "edit/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId") ?: -1
            EditBookView(bookId, viewModel, onNavigateBack = { navController.popBackStack() })
        }
    }
}
