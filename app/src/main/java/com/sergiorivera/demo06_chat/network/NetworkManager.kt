package com.sergiorivera.demo06_chat.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val loggingInterceptor = HttpLoggingInterceptor()
    //loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.1.200.72:3000/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofit.create(ChatService::class.java)
}