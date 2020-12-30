package com.android.ghcasestudy.data.source.local.DAO

import androidx.room.*
import com.android.ghcasestudy.data.entities.GitUser

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