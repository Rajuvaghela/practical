package com.example.practicaltask.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.practicaltask.R
import com.example.practicaltask.adapter.PageAdapter
import com.example.practicaltask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mBinder: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinder = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpViewPager()
    }

    private fun setUpViewPager() {
        mBinder.viewPager.adapter = PageAdapter(supportFragmentManager)
        mBinder.tabLayout.setupWithViewPager(mBinder.viewPager)
    }
}