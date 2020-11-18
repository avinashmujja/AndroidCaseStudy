package com.android.sbcasestudy.base.common

import com.android.sbcasestudy.data.GitUser

interface Callback {
    fun invokeUserDetails(gitUser: GitUser,selection:Int)
}