package com.android.ghcasestudy.userStats.repository

import com.android.ghcasestudy.data.GithubAPI
import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UserFollowersRepository {
    suspend fun invoke(loginName: String,
                       per_page : Int,
                       page : Int) : List<GitUser>
}

class UserFollowersRepositoryImpl @Inject
constructor(private val githubAPI: GithubAPI) : UserFollowersRepository {
    override suspend fun invoke(loginName: String, per_page: Int, page: Int):
            List<GitUser> = withContext(Dispatchers.Default) {
        return@withContext githubAPI.getUserFollowersList(loginName, Utils.PER_PAGE_COUNT, page)
    }

}