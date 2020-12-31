package com.android.ghcasestudy.domain

import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.data.source.network.repository.UserDetailRepository
import javax.inject.Inject

interface UserDetailUsecase {
    suspend fun invoke(loginName: String) : GitUser
}

class UserDetailUsecaseImpl @Inject
constructor(private val userDetailRepository: UserDetailRepository) : UserDetailUsecase{

    override suspend fun invoke(loginName: String) : GitUser {
        return userDetailRepository.invokeUserDetailsByLoginName(loginName)
    }

}