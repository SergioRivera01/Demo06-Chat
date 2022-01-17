package com.sergiorivera.demo06_chat.network

data class MsgBody(
    val userId: Int,
    val msg: String,
    val date: String
)
