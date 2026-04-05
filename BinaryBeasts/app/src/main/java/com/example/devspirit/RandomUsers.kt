package com.example.devspirit
import retrofit2.Call
import retrofit2.http.GET

interface RandomUsers {
    @GET("api/")
    fun getData(): Call<RandomUser>

}