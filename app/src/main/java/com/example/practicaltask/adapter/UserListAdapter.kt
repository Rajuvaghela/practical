package com.example.practicaltask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaltask.R
import com.example.practicaltask.databinding.RowUserBinding
import com.example.practicaltask.litsner.RecyclerRowLitsner
import com.example.practicaltask.model.UserList
import java.util.*

class UserListAdapter(private val mContext: Context, private val items: ArrayList<UserList>,
                      private val listener: RecyclerRowLitsner) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: RowUserBinding = DataBindingUtil.inflate(inflater, R.layout.row_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        Glide.with(mContext).load(items[position].avatar).placeholder(R.mipmap.ic_launcher).into(holder.binding.imgUser)
        /*if (position == (items.size - 1)) {
            listener.loadMore(1)
        }*/
    }

    class ViewHolder(val binding: RowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserList) {
            binding.model = data
            binding.executePendingBindings()
        }
    }

}