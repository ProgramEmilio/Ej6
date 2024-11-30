package com.example.ej6.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ej6.Retrofit.RetrofitInstance
import com.example.ej6.models.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> get() = _books

    fun fetchBooks() {
        viewModelScope.launch {
            try {
                _books.value = RetrofitInstance.api.getBooks()
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

    fun updateBook(id: Int, book: Book) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.updateBook(id, book)
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
                fetchBooks()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
