package com.example.practicaltask.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.practicaltask.R
import com.example.practicaltask.adapter.UserListAdapter
import com.example.practicaltask.databinding.FragmentListBinding
import com.example.practicaltask.litsner.RecyclerRowLitsner
import com.example.practicaltask.model.UserList
import com.example.practicaltask.viewmodel.MainActivityViewModel
import java.util.*

class UserListFragment : Fragment(), RecyclerRowLitsner {

    lateinit var mBinder: FragmentListBinding
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var adapter: UserListAdapter
    var items: ArrayList<UserList> = ArrayList()
    var TOTAL_PAGE = 0
    var CURRENT_PAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinder = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.fragment_list, container, false)
        prepareLayout()
        return mBinder.root
    }

    private fun prepareLayout() {
        adapter = UserListAdapter(requireActivity(), items, this)
        mBinder.adapter = adapter

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        getUserListFromServer()
    }

    private fun getUserListFromServer() {
        mainActivityViewModel.getUser(CURRENT_PAGE.toString())!!.observe(requireActivity(), {
            it.let {
                TOTAL_PAGE = it.totalPages
                if (it.page == 1) {
                    items.clear()
                }
                items.addAll(it.userList)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun rowClick(pos: Int, flag: Int) {

    }

    override fun loadMore(pos: Int) {
        if (CURRENT_PAGE < TOTAL_PAGE) {
            CURRENT_PAGE += 1
            getUserListFromServer()
        }

    }
}