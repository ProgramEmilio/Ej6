package com.example.ej6.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ej6.models.Book
import com.example.ej6.viewmodels.BookViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookView(viewModel: BookViewModel, onNavigateBack: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var pages by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Add Book",
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
        ContentAddBookView(
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
                val uniqueId = viewModel.generateUniqueId()
                viewModel.addBook(
                    Book(
                        id = uniqueId,
                        title = title,
                        author = author,
                        genre = genre,
                        price = price.toDoubleOrNull() ?: 0.0,
                        pages = pages.toIntOrNull() ?: 0
                    )
                )
                onNavigateBack()
            }
        )
    }
}

@Composable
fun ContentAddBookView(
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
            Text(text = "Add Book")
        }
    }
}
