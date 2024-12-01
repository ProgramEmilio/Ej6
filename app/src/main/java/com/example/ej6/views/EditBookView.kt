package com.example.ej6.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ej6.viewmodels.BookViewModel



@Composable
fun EditBookView(bookId: Int, viewModel: BookViewModel, onNavigateBack: () -> Unit) {
    val book by remember { mutableStateOf(viewModel.books.value.find { it.id == bookId }) }

    var title by remember { mutableStateOf(book?.title ?: "") }
    var author by remember { mutableStateOf(book?.author ?: "") }
    var genre by remember { mutableStateOf(book?.genre ?: "") }
    var price by remember { mutableStateOf(book?.price?.toString() ?: "") }
    var pages by remember { mutableStateOf(book?.pages?.toString() ?: "") }

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
            val updatedBook = book?.copy(
                title = title,
                author = author,
                genre = genre,
                price = price.toDoubleOrNull() ?: 0.0,
                pages = pages.toIntOrNull() ?: 0
            )
            if (updatedBook != null) {
                viewModel.updateBook(updatedBook)
                onNavigateBack()
            }
        }) {
            Text("Update Book")
        }
    }
}