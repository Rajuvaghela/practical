package com.example.practicaltask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaltask.model.UserResponse
import com.example.practicaltask.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<UserResponse>? = null

    fun getUser(currentPage: String): LiveData<UserResponse>? {
        servicesLiveData = MainActivityRepository.getServicesApiCall(currentPage)
        return servicesLiveData
    }
}