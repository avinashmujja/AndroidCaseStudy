package com.android.ghcasestudy.userDetails.repository

import com.android.ghcasestudy.data.GithubAPI
import com.android.ghcasestudy.data.entities.GitUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UserDetailRepository {
    suspend fun invokeUserDetailsByLoginName(name : String) : GitUser
}

class UserDetailRepositoryImpl @Inject
constructor(private val githubAPI: GithubAPI) : UserDetailRepository {
    override suspend fun invokeUserDetailsByLoginName(name: String): GitUser = withContext(
        Dispatchers.Default){
        return@withContext githubAPI.getUserDetailsByLoginName(name)
    }
}