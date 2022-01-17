package com.sergiorivera.demo06_chat.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MessageEntity::class], version = 1)
abstract class UtadChatDb : RoomDatabase() {
    abstract fun messageDao(): MessageDAO
}