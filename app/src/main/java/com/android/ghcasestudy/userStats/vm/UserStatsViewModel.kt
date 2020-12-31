package com.android.ghcasestudy.userStats.vm

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ghcasestudy.common.usecases.ModifyItemsUseCase
import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.userStats.usecases.UserDetailsUsecase
import kotlinx.coroutines.launch

class UserStatsViewModel @ViewModelInject
constructor(
    private val userDetailsUsecase: UserDetailsUsecase,
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

    var pageCount : Int = 1

    private val isLoadMore: ObservableField<Boolean> = ObservableField(false)

    fun fetchUserDetails(loginName : String,selection: Int) {
        _dataLoading.value = true
        viewModelScope.launch {
            try {
                val userList = userDetailsUsecase.invokeUserDetailsByName(pageCount,selection, loginName)
                userList?.let {
                    if(it.isNotEmpty()) {
                        _isItemsNotFound.value = false
                        ++pageCount
                        isLoadMore.set(true)
                        _items.postValue(modifyItemsUseCase.invoke(_items.value!!,it?.toMutableList()))
                        return@let
                    }
                    _isItemsNotFound.value = false
                    _text.value = "no items found"
                }
            } catch (ex : Exception) {
                _isItemsNotFound.value = true
                _text.value = ex.message
            } finally {
                _dataLoading.value = false
            }
        }
    }


    fun fetchNextBatchUserUserDetails(loginName : String,selection: Int) {
        if(!isLoadMore.get()!! || pageCount == 1)
            return
        _loadMore.value = true
        isLoadMore.set(false)
        viewModelScope.launch {
            try {
                val userList = userDetailsUsecase.invokeUserDetailsByName(pageCount,selection, loginName)
                userList?.let {
                    if(it.isNotEmpty()) {
                        ++pageCount
                        isLoadMore.set(true)
                        _items.postValue(modifyItemsUseCase.invoke(_items.value!!,it?.toMutableList()))
                        return@let
                    }
                }
            } catch (ex : Exception) {
            } finally {
                _loadMore.value = false
            }
        }
    }

}