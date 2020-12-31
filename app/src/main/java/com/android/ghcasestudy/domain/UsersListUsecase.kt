package com.android.ghcasestudy.domain

import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.data.entities.UserItem
import com.android.ghcasestudy.data.source.local.DAO.UserDAO
import com.android.ghcasestudy.data.source.local.repository.UpdateUserListLocalRepository
import com.android.ghcasestudy.data.source.local.repository.UserListLocalRepository
import com.android.ghcasestudy.data.source.network.repository.UserListRepository
import com.android.ghcasestudy.mapper.UserListMapper
import com.android.ghcasestudy.utils.Utils.PER_PAGE_COUNT
import javax.inject.Inject

interface UsersListUsecase {
    suspend fun invoke(item: Int, isFirstTime: Boolean) : UserItem
}

open class UsersListUsecaseImpl @Inject constructor(
    private val userListLocalRepository: UserListLocalRepository,
    private val userListRepository: UserListRepository,
    private val userListMapper : UserListMapper
) : UsersListUsecase {

    override suspend fun invoke(item: Int,isFirstTime: Boolean): UserItem {
        if(isFirstTime) {
            userListLocalRepository.invoke().also { it ->  it?.let {
                if (it.isNotEmpty()) {
                    return userListMapper.invoke(it)
                }
            }
            }
        }
        userListRepository.invoke(item, PER_PAGE_COUNT).also { it ->   it?.let {
            if(it.isNotEmpty()) {
                return userListMapper.invoke(it)
            }
        }
        }
        throw Exception("Unexpected error happend")
    }

}