package com.android.sbcasestudy.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.sbcasestudy.FakeGithubAPI
import com.android.sbcasestudy.domain.UserUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserUsecaseTest {
    private val fakeGithubAPI = FakeGithubAPI()
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val userUsecase = UserUsecase(fakeGithubAPI)

    @Test
    fun testgetUserDetailsFromLoginName() = runBlocking {
        val result = userUsecase.getUserDetailsFromLoginName("login1")
        Assert.assertTrue(result.login.equals("login1"))
    }

}