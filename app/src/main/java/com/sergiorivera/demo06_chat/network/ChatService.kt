package com.sergiorivera.demo06_chat.network

import com.sergiorivera.demo06_chat.model.Message
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatService {

    @GET("msg")
    fun getAllChats(): Call<List<Message>>

    @POST("msg")
    fun sendMessage(@Body msgBody: MsgBody): Call<List<Message>>


}