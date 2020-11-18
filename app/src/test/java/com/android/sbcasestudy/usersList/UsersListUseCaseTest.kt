package com.android.sbcasestudy.usersList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.sbcasestudy.FakeGitUser
import com.android.sbcasestudy.FakeGithubAPI
import com.android.sbcasestudy.FakeUserDAO
import com.android.sbcasestudy.domain.UsersListUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersListUseCaseTest {
    private val fakeUserDAO = FakeUserDAO()

    private val fakeGithubAPI = FakeGithubAPI()
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val usersListUseCase = UsersListUsecase(fakeUserDAO, fakeGithubAPI)

    @Test
    fun testFetchUserListItemsIsNotEmpty() = runBlocking {
        val result = usersListUseCase.fetchUserListItems(0)
        val result2 = fakeUserDAO.invokeLocalUsers()
        Assert.assertTrue(result!!.isNotEmpty())
        Assert.assertTrue(result2.isNotEmpty())
        Assert.assertTrue(result!!.size == result2.size)
    }

    @Test
    fun testFetchNextBatchUserListItemsIsNotEmpty() = runBlocking {
        val result = usersListUseCase.fetchNextBatchUserListItems(0)
        Assert.assertTrue(result!!.isNotEmpty())
    }

    @Test
    fun testLocalItemsIsEmpty() = runBlocking {
        fakeUserDAO.saveUserList(emptyList())
        val result = fakeUserDAO.invokeLocalUsers()
        Assert.assertTrue(result!!.isEmpty())
    }

    @Test
    fun testLocalItemsIsNotEmpty() = runBlocking {
        fakeUserDAO.saveUserList(FakeGitUser().setUpGitUserData(0,5))
        val result = fakeUserDAO.invokeLocalUsers()
        Assert.assertTrue(result!!.isNotEmpty())
    }

    @Test
    fun testComputeNextItemIdisNotZero() = runBlocking {
        val result = usersListUseCase.computeNextItemId(FakeGitUser().setUpGitUserData(0,5))
        Assert.assertTrue(result > 0)
    }

}