package com.example.practicaltask.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.practicaltask.R
import com.example.practicaltask.databinding.FragmentTimerBinding
import io.paperdb.Paper
import java.util.concurrent.TimeUnit

class TimerFragment : Fragment() {

    lateinit var mBinder: FragmentTimerBinding
    var counter: Long = 0
    var isTimerRunning = false
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinder = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.fragment_timer, container, false)
        prepareLayout()
        return mBinder.root
    }

    private fun prepareLayout() {
        handler = Handler()
        setButtonText()
        mBinder.btnTimer.setOnClickListener {
            isTimerRunning = if (!isTimerRunning) {
                handler?.postDelayed(runnable, 0)
                Paper.book().write("currentTime", System.currentTimeMillis())
                true
            } else {
                handler?.removeCallbacks(runnable)
                counter = 0
                mBinder.time = counter.toString()
                false
            }
            setButtonText()
            Paper.book().write("isTimerRunning", isTimerRunning)
        }
    }

    override fun onResume() {
        super.onResume()
        isTimerRunning = Paper.book().read("isTimerRunning", false)
        var localMili: Long = Paper.book().read("currentTime", 0)
        var currentMili: Long = System.currentTimeMillis()
        if (isTimerRunning) {
            counter = TimeUnit.MILLISECONDS.toSeconds(currentMili - localMili)
            handler?.postDelayed(runnable, 0)
        }
        setButtonText()
    }

    override fun onPause() {
        super.onPause()
        handler?.removeCallbacks(runnable)
    }

    private fun setButtonText() {
        if (!isTimerRunning) {
            mBinder.btnTimer.text = getString(R.string.str_start)
        } else {
            mBinder.btnTimer.text = getString(R.string.str_stop)
        }
    }

    var runnable: Runnable = object : Runnable {
        override fun run() {
            counter += 1
            mBinder.time = counter.toString()
            handler?.postDelayed(this, 1000)
        }

    }
}