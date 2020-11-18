package com.android.sbcasestudy.domain

import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.data.source.remote.GithubAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserUsecase @Inject constructor(val githubAPI: GithubAPI){

    suspend fun getUserDetailsFromLoginName(loginName: String):
            GitUser  = withContext(Dispatchers.Default){
        return@withContext githubAPI.getUserDetailsByLoginName(loginName)
    }

}