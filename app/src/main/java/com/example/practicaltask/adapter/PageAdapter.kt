package com.example.practicaltask.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.ListFragment
import com.example.practicaltask.fragment.TimerFragment
import com.example.practicaltask.fragment.UserListFragment

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                UserListFragment()
            }
            1 -> {
                TimerFragment()
            }
            else -> {
                UserListFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "User"
            }
            1 -> {
                return "Timer"
            }
        }
        return super.getPageTitle(position)
    }

}