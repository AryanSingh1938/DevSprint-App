package com.example.devspirit

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.example.devspirit.api.JokeApi
//import com.example.devspirit.api.NasaApi
//import com.example.devspirit.api.PokemonApi

import com.example.devspirit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.generateUser.setOnClickListener {
            getData()
        }
        getData()
    }

    private fun getData() {
        RetrofitInstance.randomUser.getData().enqueue(object : Callback<RandomUser> {
            override fun onResponse(call: Call<RandomUser>, response: retrofit2.Response<RandomUser>) {
                val user = response.body()?.results?.getOrNull(0)
                if (user != null) {
                    Log.d("Hello", user.toString())
                    val userName = "${user.name.first} ${user.name.last}"
                    binding.userInfo.text = userName
                    Glide.with(this@MainActivity).load(user.picture.large).into(binding.imageView)
                }
            }

            override fun onFailure(call: Call<RandomUser>, t: Throwable) {
                Log.e("MainActivity", "API call failed", t)
            }
        })

        // Fetch and display quote
        getQuote()
    }

    private fun getQuote() {
        RetrofitInstance.quoteApi.getAdvice().enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: retrofit2.Response<QuoteResponse>) {
                val quote = response.body()?.slip?.advice
                if (quote != null) {
                    Log.d("Quote", quote)
                    binding.joke.text = quote
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                Log.e("MainActivity", "Quote API call failed", t)
            }
        })
    }
}