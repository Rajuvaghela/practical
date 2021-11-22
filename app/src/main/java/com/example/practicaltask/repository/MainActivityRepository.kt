package com.example.practicaltask.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.practicaltask.model.UserResponse
import com.example.practicaltask.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val userResponse = MutableLiveData<UserResponse>()

    fun getServicesApiCall(currentPage: String): MutableLiveData<UserResponse> {

        val hashMap = HashMap<String, String>()
        hashMap["page"] = currentPage
        val call = RetrofitClient.apiInterface.getServices(hashMap)

        call.enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("DEBUG : ", t.message.toString())
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                Log.e("DEBUG : ", response.body().toString())
                userResponse.value = response.body()
                //serviceSetterGetter.value = UserResponse(msg)
            }
        })
        return userResponse
    }
}