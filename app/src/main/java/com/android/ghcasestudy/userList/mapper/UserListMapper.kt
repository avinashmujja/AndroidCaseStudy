package com.android.ghcasestudy.userList.mapper

import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.data.entities.UserItem
import com.android.ghcasestudy.utils.Utils
import javax.inject.Inject

class UserListMapper @Inject constructor() : Function1<List<GitUser>,UserItem>{
    override fun invoke(p1: List<GitUser>): UserItem {
        return UserItem(nextBatchId = Utils.fetchNextItemID(p1), gitUser = p1)
    }

}