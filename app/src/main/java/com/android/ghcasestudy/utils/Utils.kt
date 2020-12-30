package com.android.ghcasestudy.utils

import com.android.ghcasestudy.data.entities.GitUser

object Utils {

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