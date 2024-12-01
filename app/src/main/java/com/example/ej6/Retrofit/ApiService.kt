package com.example.ej6.models.Retrofit

import retrofit2.http.*

interface ApiService {
    @GET("api/Books")
    suspend fun getBooks(): List<com.example.ej6.models.Book>

    @POST("api/Books")
    suspend fun addBook(@Body book: com.example.ej6.models.Book): com.example.ej6.models.Book

    @PUT("api/Books/{id}")
    suspend fun updateBook(@Path("id") id: Int, @Body book: com.example.ej6.models.Book): com.example.ej6.models.Book

    @DELETE("api/Books/{id}")
    suspend fun deleteBook(@Path("id") id: Int)
}
