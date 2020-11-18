package com.android.sbcasestudy

import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.data.source.remote.GithubAPI
import kotlinx.coroutines.runBlocking
import retrofit2.http.Path
import retrofit2.http.Query

class FakeGithubAPI() : GithubAPI {

    private var userList: MutableList<GitUser> = mutableListOf()

    override suspend fun getUserList(since: Int, itemCount: Int): List<GitUser> = runBlocking {
        clearUserList()
        return@runBlocking FakeGitUser().setUpGitUserData(0,5)
    }

    override suspend fun getUserDetailsByLoginName(loginName: String): GitUser = runBlocking{
        val userList = FakeGitUser().setUpGitUserData(0,5)
        return@runBlocking FakeGitUser().FetchUserDetailsByLoginName(userList,loginName)
    }

    override suspend fun getUserFollowersList(
        @Path(value = "loginName") loginName: String,
        @Query(value = "per_page") per_page: Int,
        @Query(value = "page") page: Int
    ): List<GitUser> = runBlocking{
        clearUserList()
        return@runBlocking FakeGitUser().setUpGitUserData(0,10)
    }

    override suspend fun getUserFollowingList(
        @Path(value = "loginName") loginName: String,
        @Query(value = "per_page") per_page: Int,
        @Query(value = "page") page: Int
    ): List<GitUser> = runBlocking{
        clearUserList()
        return@runBlocking FakeGitUser().setUpGitUserData(0,15)
    }

    private fun clearUserList() {
        userList = mutableListOf()
    }


}