package com.sergiorivera.demo06_chat

import android.os.Bundle
import android.provider.DocumentsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.room.Room
import com.sergiorivera.demo06_chat.database.MessageEntity
import com.sergiorivera.demo06_chat.database.UtadChatDb
import com.sergiorivera.demo06_chat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
       lateinit var db: UtadChatDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        db = Room.databaseBuilder(applicationContext, UtadChatDb::class.java, "utad-chat-db")
            .allowMainThreadQueries()
            .build()



    }
}