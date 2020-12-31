package com.android.ghcasestudy.userStats.usecases

import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.userStats.repository.UserFollowingRepository
import com.android.ghcasestudy.userStats.repository.UserFollowersRepository
import com.android.ghcasestudy.utils.Utils
import javax.inject.Inject

interface UserDetailsUsecase {
    suspend fun invokeUserDetailsByName(pageNumber : Int,
                                        selection : Int,loginName:String) : List<GitUser>?
}

class UserDetailsUsecaseImpl @Inject
constructor(private val userFollowingRepository: UserFollowingRepository,
            private val userFollwersRepository: UserFollowersRepository
) : UserDetailsUsecase {

    override suspend fun invokeUserDetailsByName(
        pageNumber: Int,
        selection: Int,
        loginName: String
    ) : List<GitUser>? {
        return when(selection) {
            Utils.FOLLOWING ->
                userFollowingRepository.invoke(loginName,Utils.PER_PAGE_COUNT,pageNumber)
            Utils.FOLLOWERS ->
                userFollwersRepository.invoke(loginName,Utils.PER_PAGE_COUNT,pageNumber)
            else -> throw Exception("not supported operation")
        }
    }

}