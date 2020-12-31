package com.android.ghcasestudy.data.source.network.repository

import com.android.ghcasestudy.data.GithubAPI
import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface UserFollowingRepository {
    suspend fun invoke(loginName: String,
                       per_page : Int,
                       page : Int) : List<GitUser>
}

class UserFollowingRepositoryImpl @Inject
constructor(private val githubAPI: GithubAPI) : UserFollowingRepository{
    override suspend fun invoke(loginName: String, per_page: Int, page: Int):
            List<GitUser> = withContext(Dispatchers.Default) {
        return@withContext githubAPI.getUserFollowingList(loginName, Utils.PER_PAGE_COUNT, page)
    }

}