package com.android.ghcasestudy.common

import com.android.ghcasestudy.data.entities.GitUser

interface Callback {
    fun invokeUserDetails(gitUser: GitUser, selection:Int)
}