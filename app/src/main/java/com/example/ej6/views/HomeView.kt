package com.example.ej6.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ej6.viewmodels.BookViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: BookViewModel = viewModel(), onNavigateToAdd: () -> Unit, onNavigateToEdit: (Int) -> Unit) {
    val books = viewModel.books.value

    LaunchedEffect(Unit) {
        viewModel.fetchBooks()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book List") },
                actions = {
                    IconButton(onClick = { viewModel.fetchBooks() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) {
                Text("+")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(books) { book ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onNavigateToEdit(book.id) }) {
                    Text("${book.title} by ${book.author} - ${book.genre}",
                        Modifier.weight(1f))
                    IconButton(onClick = { viewModel.deleteBook(book.id); viewModel.fetchBooks() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}