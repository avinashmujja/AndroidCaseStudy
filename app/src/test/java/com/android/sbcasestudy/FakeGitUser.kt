package com.android.sbcasestudy

import com.android.sbcasestudy.data.GitUser

class FakeGitUser {

    fun setUpGitUserData(item1:Int,item2:Int): List<GitUser> {
        return (item1 until item2).map {
            GitUser(login = "login$it",id = it,node_id = "node_id$it",avatar_url = "avatar$it",url = "url$it",
            name = "name$it",company = "company$it",blog = "blog$it",location = "location$it",email = "email$it",
            bio = "bio$it",followers = it,following = it)
        }
    }

    fun FetchUserDetailsByLoginName(items: List<GitUser>,loginName : String): GitUser {
        val iterator = items.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if(item.login.equals(loginName,ignoreCase = true)) {
                return item
            }
        }
        return items[0]
    }

}