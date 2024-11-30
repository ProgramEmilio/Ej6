package com.example.ej6.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ej6.models.Book
import com.example.ej6.viewmodels.BooksViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    viewModel: BooksViewModel,
    navController: NavController // Se añade el controlador de navegación
) {
    val books by viewModel.books.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Book List") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Navegar a AddBookScreen
                    navController.navigate("addBook")
                }
            ) {
                Text("+")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(books) { book ->
                ListItem(
                    headlineContent = { Text(book.title) },
                    supportingContent = { Text("Author: ${book.author}") },
                    trailingContent = {
                        Row {
                            IconButton(
                                onClick = {
                                    // Navegar a EditBookScreen con el ID del libro
                                    navController.navigate("editBook/${book.id}")
                                }
                            ) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit")
                            }
                            IconButton(
                                onClick = { viewModel.deleteBook(book.id) }
                            ) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                )
            }
        }
    }
}
