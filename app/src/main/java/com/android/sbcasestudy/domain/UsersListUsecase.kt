package com.android.sbcasestudy.domain

import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.data.source.local.UserDAO
import com.android.sbcasestudy.data.source.remote.GithubAPI
import com.android.sbcasestudy.utils.Utils
import com.android.sbcasestudy.utils.Utils.PER_PAGE_COUNT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class UsersListUsecase @Inject constructor(
    private val gitUserDAO: UserDAO,
    private val githubAPI: GithubAPI) {

    suspend fun fetchUserListItems(item:Int) : List<GitUser>? = withContext(Dispatchers.Default) {
        val items = gitUserDAO.invokeLocalUsers()
        items?.let {
            if(it.isNotEmpty()) {
                return@withContext items
            }
        }
        val userItems : List<GitUser> = githubAPI.getUserList(item, PER_PAGE_COUNT)
        userItems?.let {
            if(it.isNotEmpty()) {
                saveUsersIntoLocal(it)
                return@withContext it
            }
        }
        throw Exception("Unexpected error happend")
    }

    suspend fun fetchNextBatchUserListItems(item:Int) : List<GitUser>? = withContext(Dispatchers.Default) {
        val userItems : List<GitUser> = githubAPI.getUserList(item, PER_PAGE_COUNT)
        userItems?.let {
            if(it.isNotEmpty()) {
                saveUsersIntoLocal(it)
                return@withContext it
            }
        }
        throw Exception("Unexpected error happend")
    }

    fun addAll(items1: MutableList<GitUser>, items2: List<GitUser>?): MutableList<GitUser>? {
        return Utils.addItems(items1,items2?.toMutableList())
    }

    fun computeNextItemId(it: List<GitUser>): Int {
        return Utils.fetchNextItemID(it)
    }

    private fun saveUsersIntoLocal(userItems: List<GitUser>) {
        gitUserDAO.saveUserList(userItems)
    }

}