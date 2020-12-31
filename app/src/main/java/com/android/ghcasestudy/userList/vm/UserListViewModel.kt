package com.android.ghcasestudy.userList.vm

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ghcasestudy.common.usecases.ModifyItemsUseCase
import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.userList.usecases.UsersListUsecase
import kotlinx.coroutines.launch

class UserListViewModel @ViewModelInject
constructor(
    private val usersListUsecase: UsersListUsecase,
    private val modifyItemsUseCase: ModifyItemsUseCase
) : ViewModel() {

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
                val userList = usersListUsecase.invoke(lastItemSince,true)
                userList?.let {
                    _isItemsNotFound.value = false
                    lastItemSince = it.nextBatchId
                    isLoadMore.set(true)
                    _items.postValue(modifyItemsUseCase.invoke(_items.value!!,
                        it.gitUser.toMutableList()
                    ))
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
                val userList = usersListUsecase.invoke(lastItemSince,false)
                userList?.let {
                    lastItemSince = it.nextBatchId
                    isLoadMore.set(true)
                    _items.postValue(modifyItemsUseCase.invoke(_items.value!!,
                        it.gitUser.toMutableList()))
                }
            } catch (ex : Exception) {
            } finally {
                _loadMore.value = false
            }
        }
    }

}