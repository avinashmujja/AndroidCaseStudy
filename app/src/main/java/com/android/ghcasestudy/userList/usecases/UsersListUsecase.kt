package com.android.ghcasestudy.userList.usecases

import com.android.ghcasestudy.data.entities.UserItem
import com.android.ghcasestudy.userList.repository.UserListRepository
import com.android.ghcasestudy.userList.mapper.UserListMapper
import com.android.ghcasestudy.utils.Utils.PER_PAGE_COUNT
import javax.inject.Inject

interface UsersListUsecase {
    suspend fun invoke(item: Int, isFirstTime: Boolean) : UserItem
}

open class UsersListUsecaseImpl @Inject constructor(
    private val userListRepository: UserListRepository,
    private val userListMapper : UserListMapper
) : UsersListUsecase {

    override suspend fun invoke(item: Int,isFirstTime: Boolean): UserItem {
        userListRepository.invoke(item, PER_PAGE_COUNT).also { it ->   it?.let {
            if(it.isNotEmpty()) {
                return userListMapper.invoke(it)
            }
        }
        }
        throw Exception("Unexpected error happend")
    }

}