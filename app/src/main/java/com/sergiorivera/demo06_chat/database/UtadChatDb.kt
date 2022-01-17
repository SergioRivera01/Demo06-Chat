package com.sergiorivera.demo06_chat.database

import androidx.room.Database

@Database(entities = [MessageEntity::class], version = 1)
abstract class UtadChatDb {
    abstract fun messageDao(): MessageDAO
}