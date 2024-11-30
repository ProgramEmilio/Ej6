package com.example.ej6.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ej6.models.Book
import com.example.ej6.viewmodels.BooksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBookScreen(viewModel: BooksViewModel, onBookAdded: () -> Unit) {
    // Estado mutable para los campos del formulario
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Book") }) },
        content = { innerPadding ->
            // Contenido principal de la pantalla
            Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
                // Campo para el título
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                // Campo para el autor
                TextField(
                    value = author,
                    onValueChange = { author = it },
                    label = { Text("Author") },
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                // Campo para la descripción
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Botón para guardar
                Button(onClick = {
                    // Agregar el libro usando el ViewModel
                    viewModel.addBook(Book(id = 0, title = title, author = author, description = description))
                    onBookAdded() // Llamar al callback
                }) {
                    Text("Save")
                }
            }
        }
    )
}
