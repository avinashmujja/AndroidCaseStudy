package com.android.sbcasestudy.userDetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.sbcasestudy.FakeGitUser
import com.android.sbcasestudy.FakeGithubAPI
import com.android.sbcasestudy.domain.UserDetailsUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDetailsUsecaseTest {
    private val fakeGithubAPI = FakeGithubAPI()
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val userDetailsUsecase = UserDetailsUsecase(fakeGithubAPI)

    @Test
    fun testUserDetailsByLoginNameIsNotEmpty() = runBlocking {
        val result = userDetailsUsecase.getUserDetailsByLoginName(0,1,"test")
        Assert.assertTrue(result!!.isNotEmpty())
    }

    @Test
    fun testUserDetailsAddFunctionality() = runBlocking {
        val result = userDetailsUsecase.addAll(FakeGitUser().setUpGitUserData(0,5).toMutableList(),FakeGitUser().setUpGitUserData(6,11));
        Assert.assertTrue(result!!.size == 10)
    }
}