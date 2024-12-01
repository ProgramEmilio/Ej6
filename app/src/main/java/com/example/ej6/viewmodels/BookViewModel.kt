package com.example.ej6.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ej6.Retrofit.RetrofitInstance
import com.example.ej6.models.Book
import kotlinx.coroutines.launch
import kotlin.random.Random



class BookViewModel : ViewModel() {
    var books = mutableStateOf<List<Book>>(emptyList())

    fun generateUniqueId(): Int {
        val existingIds = books.value.map { it.id }.toSet()
        var newId: Int
        do {
            newId = Random.nextInt(1, 966)
        } while (newId in existingIds)
        return newId
    }

    fun fetchBooks() {
        viewModelScope.launch {
            try {
                books.value = RetrofitInstance.api.getBooks()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addBook(book: Book) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.addBook(book)
                fetchBooks()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.updateBook(book.id, book)
                fetchBooks()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteBook(id: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deleteBook(id)
                books.value = books.value.filter { it.id != id }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}