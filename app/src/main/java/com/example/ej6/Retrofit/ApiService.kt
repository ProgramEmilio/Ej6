package com.example.ej6.Retrofit

import com.example.ej6.models.Book
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("api/Books")
    suspend fun getBooks(): List<Book>

    @POST("api/Books")
    suspend fun addBook(@Body book: Book): Book

    @PUT("api/Books/{id}")
    suspend fun updateBook(@Path("id") id: Int, @Body book: Book): Book

    @DELETE("api/Books/{id}")
    suspend fun deleteBook(@Path("id") id: Int)
}
