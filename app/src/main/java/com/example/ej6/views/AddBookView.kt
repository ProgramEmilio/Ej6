package com.example.ej6.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ej6.models.Book
import com.example.ej6.viewmodels.BookViewModel


@Composable
fun AddBookView(viewModel: BookViewModel, onNavigateBack: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var pages by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        TextField(value = author, onValueChange = { author = it }, label = { Text("Author") })
        TextField(value = genre, onValueChange = { genre = it }, label = { Text("Genre") })
        TextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
        TextField(value = pages, onValueChange = { pages = it }, label = { Text("pages") })
        Button(onClick = { onNavigateBack() }) {
            Text("Return")
        }
        Button(onClick = {
            val uniqueId = viewModel.generateUniqueId()
            viewModel.addBook(
                Book(uniqueId, title, author, genre, price.toDouble(), pages.toInt())
            )
            onNavigateBack()
        }) {
            Text("Add Book")
        }
    }
}