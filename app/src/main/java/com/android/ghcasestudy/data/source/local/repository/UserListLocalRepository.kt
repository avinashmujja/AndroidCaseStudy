package com.android.ghcasestudy.data.source.local.repository

import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.data.source.local.DAO.UserDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UserListLocalRepository {
    suspend fun invoke() : List<GitUser>
}

class UserListLocalRepositoryImpl @Inject
constructor(val userDAO: UserDAO) : UserListLocalRepository {
    override suspend fun invoke(): List<GitUser> = withContext(Dispatchers.Default){
        return@withContext userDAO.invokeLocalUsers()
    }

}