package com.example.devspirit

import retrofit2.Call
import retrofit2.http.GET

interface QuoteApi {
    @GET("advice")
    fun getAdvice(): Call<QuoteResponse>
}
