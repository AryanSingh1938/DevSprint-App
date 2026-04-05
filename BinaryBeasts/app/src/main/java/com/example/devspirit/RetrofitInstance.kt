package com.example.devspirit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val randomUserRetrofit by lazy {
        Retrofit.Builder().baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val kanyeRetrofit by lazy {
        Retrofit.Builder().baseUrl("https://api.kanye.rest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val quoteRetrofit by lazy {
        Retrofit.Builder().baseUrl("https://api.adviceslip.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val randomUser by lazy {
        randomUserRetrofit.create(RandomUsers::class.java)
    }

    val kanyeApi by lazy {
        kanyeRetrofit.create(KanyeRest::class.java)
    }

    val quoteApi by lazy {
        quoteRetrofit.create(QuoteApi::class.java)
    }
}