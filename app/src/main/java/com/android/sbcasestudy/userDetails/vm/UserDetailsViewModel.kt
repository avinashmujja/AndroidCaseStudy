package com.android.sbcasestudy.userDetails.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.domain.UserDetailsUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(private val userDetailsUsecase: UserDetailsUsecase) : ViewModel(){

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
                val userList = userDetailsUsecase.getUserDetailsByLoginName(pageCount,selection, loginName)
                userList?.let {
                    if(it.isNotEmpty()) {
                        _isItemsNotFound.value = false
                        ++pageCount
                        isLoadMore.set(true)
                        _items.postValue(userDetailsUsecase.addAll(_items.value!!,it))
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
        viewModelScope.launch {
            try {
                val userList = userDetailsUsecase.getUserDetailsByLoginName(pageCount,selection, loginName)
                userList?.let {
                    if(it.isNotEmpty()) {
                        ++pageCount
                        isLoadMore.set(true)
                        _items.postValue(userDetailsUsecase.addAll(_items.value!!,it))
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