package com.android.sbcasestudy

import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.data.source.local.UserDAO
import kotlinx.coroutines.runBlocking

class FakeUserDAO() : UserDAO {

    private var userList: MutableList<GitUser> = mutableListOf()

    override suspend fun invokeLocalUsers(): List<GitUser> = runBlocking {
        return@runBlocking userList
    }

    override fun saveUserDetails(user: GitUser?) = runBlocking{
        TODO("Not yet implemented")
    }

    override fun invokeUserByLogin(login: String): GitUser = runBlocking{
        TODO("Not yet implemented")
    }

    override fun saveUserList(repositories: List<GitUser>) {
        userList.addAll(repositories)
    }

    fun clearUserList() {
        userList = mutableListOf()
    }
}