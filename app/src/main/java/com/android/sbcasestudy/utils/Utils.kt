package com.android.sbcasestudy.utils

import androidx.databinding.ObservableField
import com.android.sbcasestudy.data.GitUser

object Utils {
    fun addItems(
        items1: MutableList<GitUser> ,
        items2: MutableList<GitUser>?
    ): MutableList<GitUser>? {
        val list:MutableList<GitUser> = items1
        list.addAll(items2!!)
        return list
    }

    fun fetchNextItemID(items: List<GitUser>): Int {
        items?.let { it.isNotEmpty().let {
            return items[items.size - 1].id?:0
        } }
        return 0
    }

    const val PER_PAGE_COUNT : Int = 10
    const val USER :String = "user"
    const val SELECTION :String = "selection"
    const val FOLLOWING = 1
    const val FOLLOWERS = 2
}