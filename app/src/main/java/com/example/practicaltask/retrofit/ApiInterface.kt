package com.example.practicaltask.retrofit

import com.example.practicaltask.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("users")
    fun getServices(@QueryMap param: HashMap<String, String>): Call<UserResponse>

}