package com.android.ghcasestudy.common.usecases

import com.android.ghcasestudy.data.entities.GitUser
import javax.inject.Inject

interface ModifyItemsUseCase {
     suspend fun invoke(
        items1: MutableList<GitUser>,
        items2: MutableList<GitUser>?
    ): MutableList<GitUser>?
}

class ModifyitemUseCaseImpl @Inject constructor() : ModifyItemsUseCase {

    override suspend fun invoke(
        items1: MutableList<GitUser>,
        items2: MutableList<GitUser>?
    ): MutableList<GitUser>? {
        return addItems(items1,items2?.toMutableList())
    }

    fun addItems(
        items1: MutableList<GitUser>,
        items2: MutableList<GitUser>?
    ): MutableList<GitUser>? {
        val list:MutableList<GitUser> = items1
        list.addAll(items2!!)
        return list
    }

}