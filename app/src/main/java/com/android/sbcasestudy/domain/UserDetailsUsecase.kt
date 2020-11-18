package com.android.sbcasestudy.domain

import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.data.source.remote.GithubAPI
import com.android.sbcasestudy.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDetailsUsecase @Inject constructor(private val githubAPI: GithubAPI) {


    suspend fun getUserDetailsByLoginName(pageNumber : Int, selection : Int,loginName:String) :
            List<GitUser>? = withContext(Dispatchers.Default) {
        when(selection) {
            Utils.FOLLOWING ->
                return@withContext githubAPI.getUserFollowingList(loginName,Utils.PER_PAGE_COUNT,pageNumber)
            Utils.FOLLOWERS ->
                return@withContext githubAPI.getUserFollowersList(loginName,Utils.PER_PAGE_COUNT,pageNumber)
            else -> throw Exception("not supported operation")
        }
    }

    fun addAll(items1: MutableList<GitUser>, items2: List<GitUser>?): MutableList<GitUser>? {
        return Utils.addItems(items1,items2?.toMutableList())
    }


}