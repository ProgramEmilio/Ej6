package com.example.ej6.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ej6.viewmodels.BookViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBookView(bookId: Int, viewModel: BookViewModel, onNavigateBack: () -> Unit) {
    val book by remember { mutableStateOf(viewModel.books.value.find { it.id == bookId }) }
    var title by remember { mutableStateOf(book?.title ?: "") }
    var author by remember { mutableStateOf(book?.author ?: "") }
    var genre by remember { mutableStateOf(book?.genre ?: "") }
    var price by remember { mutableStateOf(book?.price?.toString() ?: "") }
    var pages by remember { mutableStateOf(book?.pages?.toString() ?: "") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Edit Book",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        ContentEditBookView(
            paddingValues = it,
            title = title,
            author = author,
            genre = genre,
            price = price,
            pages = pages,
            onTitleChange = { title = it },
            onAuthorChange = { author = it },
            onGenreChange = { genre = it },
            onPriceChange = { price = it },
            onPagesChange = { pages = it },
            onSave = {
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
            }
        )
    }
}

@Composable
fun ContentEditBookView(
    paddingValues: PaddingValues,
    title: String,
    author: String,
    genre: String,
    price: String,
    pages: String,
    onTitleChange: (String) -> Unit,
    onAuthorChange: (String) -> Unit,
    onGenreChange: (String) -> Unit,
    onPriceChange: (String) -> Unit,
    onPagesChange: (String) -> Unit,
    onSave: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            label = { Text(text = "Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = author,
            onValueChange = onAuthorChange,
            label = { Text(text = "Author") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = genre,
            onValueChange = onGenreChange,
            label = { Text(text = "Genre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = price,
            onValueChange = onPriceChange,
            label = { Text(text = "Price") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(
            value = pages,
            onValueChange = onPagesChange,
            label = { Text(text = "Pages") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        Button(
            onClick = onSave,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(text = "Update Book")
        }
    }
}
