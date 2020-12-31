package com.android.ghcasestudy.userList.repository

import com.android.ghcasestudy.data.GithubAPI
import com.android.ghcasestudy.data.entities.GitUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UserListRepository {
    suspend fun invoke(since : Int,itemCount : Int) : List<GitUser>
}

class UserListRepositoryImpl @Inject constructor(val githubAPI: GithubAPI) : UserListRepository {
    override suspend fun invoke(since: Int, itemCount: Int):
            List<GitUser> = withContext(Dispatchers.Default) {
        return@withContext githubAPI.getUserList(since, itemCount)
    }
}