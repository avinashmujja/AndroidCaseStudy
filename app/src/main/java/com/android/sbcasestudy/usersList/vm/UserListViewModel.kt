package com.android.sbcasestudy.usersList.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.domain.UsersListUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val githubUsersUsecase: UsersListUsecase) : ViewModel() {

    private val _items = MutableLiveData<MutableList<GitUser>>().apply { value = mutableListOf() }
    val items: LiveData<MutableList<GitUser>> = _items


    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _loadMore = MutableLiveData<Boolean>()
    val loadMore: LiveData<Boolean> = _loadMore

    private val _isItemsNotFound = MutableLiveData<Boolean>()
    val isItemsNotFound: LiveData<Boolean> = _isItemsNotFound

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text

    private var lastItemSince : Int = 0

    private val isLoadMore: ObservableField<Boolean> = ObservableField(false)

    fun fetchUserProfiles() {
        _dataLoading.value = true
        viewModelScope.launch {
            try {
                val userList = githubUsersUsecase.fetchUserListItems(lastItemSince)
                userList?.let {
                    _isItemsNotFound.value = false
                    lastItemSince = githubUsersUsecase.computeNextItemId(it)
                    isLoadMore.set(true)
                    _items.postValue(githubUsersUsecase.addAll(_items.value!!,it))
                    return@let
                }
            } catch (ex : Exception) {
                _isItemsNotFound.value = true
                _text.value = ex.message
            } finally {
                _dataLoading.value = false
            }
        }
    }


    fun fetchNextBatchUserProfiles() {
        if(!isLoadMore.get()!!)
            return
        _loadMore.value = true
        isLoadMore.set(false)
        viewModelScope.launch {
            try {
                val userList = githubUsersUsecase.fetchNextBatchUserListItems(lastItemSince)
                userList?.let {
                    lastItemSince = githubUsersUsecase.computeNextItemId(it)
                    isLoadMore.set(true)
                    _items.postValue(githubUsersUsecase.addAll(_items.value!!,it))
                }
            } catch (ex : Exception) {
            } finally {
                _loadMore.value = false
            }
        }
    }

}