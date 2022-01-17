package com.sergiorivera.demo06_chat.ui

import android.icu.lang.UCharacter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sergiorivera.demo06_chat.databinding.FragmentChatBinding
import com.sergiorivera.demo06_chat.model.Message
import com.sergiorivera.demo06_chat.network.ChatService
import com.sergiorivera.demo06_chat.network.MsgBody
import com.sergiorivera.demo06_chat.network.NetworkManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }


    val adapter = ChatAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMsg.adapter = adapter
        binding.rvMsg.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,true)
        val message:MutableList<Message> = mutableListOf()

        adapter.submitList(message)

        binding.btnSend.setOnClickListener {
           sendMessage()
        }
        getMessages()

    }

    private fun getMessages() {
        NetworkManager.service.getAllChats().enqueue(object : Callback<List<Message>>{
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if(response.isSuccessful){
                    adapter.submitList(response.body())
                }else{
                    Toast.makeText(context, "Hay un error en la peticion", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                Toast.makeText(context, "Hay un error en la peticion", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun sendMessage(){
        val text = binding.etMsg.text.toString()
        val date = Date().time.toString()
        val msgBody = MsgBody(1,text, date)

        NetworkManager.service.sendMessage(msgBody).enqueue(object: Callback<List<Message>>{
            override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
                if (response.isSuccessful){
                    adapter.submitList(response.body())
                }else{
                    Toast.makeText(context, "Hay un error en la peticion", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Message>>, t: Throwable) {
                Toast.makeText(context, "Hay un error en la peticion", Toast.LENGTH_SHORT).show()
            }

        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
