package com.android.sbcasestudy.user.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.domain.UserUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userUsecase: UserUsecase) : ViewModel() {

    val data: ObservableField<GitUser> = ObservableField()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun fetchUserDetails(loginName: String) {
        _dataLoading.value = true
        viewModelScope.launch {
            try {
                val userDetails = userUsecase.getUserDetailsFromLoginName(loginName)
                data.set(userDetails)
            } catch (ex : Exception) {

            } finally {
                _dataLoading.value = false
            }
        }
    }

}