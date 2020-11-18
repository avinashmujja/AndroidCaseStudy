package com.android.sbcasestudy.data.source.local

import androidx.room.*
import com.android.sbcasestudy.data.GitUser

@Dao
interface UserDAO {
    @Query("SELECT * FROM git_user")
    suspend fun invokeLocalUsers():List<GitUser>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserDetails(user: GitUser?)

    @Query("SELECT * FROM git_user WHERE login = :login")
    fun invokeUserByLogin(login: String): GitUser

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserList(repositories: List<GitUser>)
}