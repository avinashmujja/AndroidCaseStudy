package com.android.ghcasestudy.domain

import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.data.source.local.repository.UpdateUserListLocalRepository
import javax.inject.Inject

interface SaveUserListUsecase {
    suspend fun saveUserList(repositories: List<GitUser>)
}

class SaveUserListUsecaseImpl @Inject
constructor(val updateUserListLocalRepository: UpdateUserListLocalRepository) :
    SaveUserListUsecase {
    override suspend fun saveUserList(repositories: List<GitUser>) {
        updateUserListLocalRepository.invoke(repositories = repositories)
    }

}