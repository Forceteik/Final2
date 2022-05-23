package com.example.final2

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("characters/all")
    fun getPosts(): Call<MutableList<PostModel>>
}