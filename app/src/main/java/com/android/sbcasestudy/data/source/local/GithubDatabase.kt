package com.android.sbcasestudy.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.sbcasestudy.data.GitUser

@Database(entities = [GitUser::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase(){
    abstract val usersDAO: UserDAO
}