package com.android.ghcasestudy.data

import com.android.ghcasestudy.data.entities.GitUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPI {
    @GET("users")
    suspend fun getUserList(@Query("since") since: Int,
                            @Query("per_page") itemCount: Int): List<GitUser>

    @GET("users/{loginName}")
    suspend fun getUserDetailsByLoginName(@Path("loginName") loginName : String): GitUser

    @GET("users/{loginName}/following")
    suspend fun getUserFollowersList(@Path("loginName") loginName: String,
                                     @Query("per_page") per_page : Int,
                                     @Query("page") page : Int) : List<GitUser>

    @GET("users/{loginName}/followers")
    suspend fun getUserFollowingList(@Path("loginName") loginName: String,
                                     @Query("per_page") per_page : Int,
                                     @Query("page") page : Int) : List<GitUser>
}