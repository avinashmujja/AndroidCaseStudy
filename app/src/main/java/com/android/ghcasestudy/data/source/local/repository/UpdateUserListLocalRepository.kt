package com.android.ghcasestudy.data.source.local.repository

import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.data.source.local.DAO.UserDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UpdateUserListLocalRepository {
    suspend fun invoke(repositories: List<GitUser>)
}

class UpdateUserListLocalRepositoryImpl @Inject
constructor(val userDAO: UserDAO) : UpdateUserListLocalRepository {
    override suspend fun invoke(repositories: List<GitUser>) = withContext(Dispatchers.Default) {
        userDAO.saveUserList(repositories = repositories)
    }
}