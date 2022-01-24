package com.sergiorivera.demo06_chat

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.navigation.findNavController
import androidx.room.Room
import com.sergiorivera.demo06_chat.database.MessageEntity
import com.sergiorivera.demo06_chat.database.UtadChatDb
import com.sergiorivera.demo06_chat.databinding.ActivityMainBinding

private const val CHANEl_ID = "1"
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

    //******Crear noticiaciones********//

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_DOCUMENT
        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notificacion = NotificationCompat.Builder(this, CHANEl_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Titulo de la notidficación")
            .setContentText("Texto de la notidficacion")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val channelName = "Mensajes directo"
        val channelDescription = "Recibiras todos los mensajes directos por esta via"
        val importance = NotificationManager.IMPORTANCE_HIGH

        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANEl_ID, channelName, importance).apply {
                description = channelDescription
            }
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(1, notificacion)

    }
}