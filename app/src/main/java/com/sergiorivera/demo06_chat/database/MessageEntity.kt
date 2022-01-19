package com.sergiorivera.demo06_chat.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class MessageEntity(
    @PrimaryKey(autoGenerate = true) val id : Int? = 0,
    @ColumnInfo(name = "text") val text : String,
    @ColumnInfo(name = "date", defaultValue = "1111")val date : String? = null,
    @ColumnInfo(name = "user_Id" ) val userId : Int
)

