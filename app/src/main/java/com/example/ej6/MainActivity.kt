package com.example.ej6

import AppNavHost
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

        AppNavHost(viewModel = viewModel)
    }
}
