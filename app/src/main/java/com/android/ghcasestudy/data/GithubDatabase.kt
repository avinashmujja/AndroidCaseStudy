package com.android.ghcasestudy.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.data.source.local.DAO.UserDAO

@Database(entities = [GitUser::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase(){
    abstract val usersDAO: UserDAO
}