package com.example.practicaltask

import android.app.Application
import io.paperdb.Paper

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }
}