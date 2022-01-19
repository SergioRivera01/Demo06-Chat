package com.sergiorivera.demo06_chat.database

import androidx.room.*

@Dao
interface MessageDAO {
    @Query("select * from message")
    fun findAll(): List<MessageEntity>

    @Query("select * from message order by date")
    fun findAllShorted(): List<MessageEntity>

    @Query("select * from message where user_Id = :userId")
    fun findAllMessageByUserId(userId : Int): List<MessageEntity>

    @Insert
    fun createMessage(messageEntity: MessageEntity)

    @Insert
    fun createMessageS(list: List<MessageEntity>)

    @Update
    fun updateMessage(messageEntity: MessageEntity)

    @Delete
    fun deleteMessage(messageEntity: MessageEntity)
}

//CRUD