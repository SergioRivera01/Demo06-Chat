package com.sergiorivera.demo06_chat.model

data class Message(
    val userId: Int,
    val msgId: Int,
    val text: String,
    val date: String
)
