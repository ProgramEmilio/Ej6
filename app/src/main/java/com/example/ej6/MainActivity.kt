package com.example.ej6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.ej6.ui.theme.Ej6Theme
import com.example.ej6.viewmodels.BooksViewModel
import com.example.ej6.views.AppNavHost   // Asegúrate de tener esta importación

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ej6Theme {
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp() {
        // Crear el NavController
        val navController = rememberNavController()
        // Pasar el navController y viewModel a AppNavHost
        AppNavHost(navController = navController, viewModel = viewModel)
    }
}