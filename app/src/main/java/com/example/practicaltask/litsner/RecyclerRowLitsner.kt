package com.example.practicaltask.litsner

interface RecyclerRowLitsner {
    fun rowClick(pos: Int, flag: Int)

    fun loadMore(pos: Int)
}