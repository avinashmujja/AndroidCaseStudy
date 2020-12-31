package com.android.ghcasestudy.userDetails.vm

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.userDetails.usecases.UserDetailUsecase
import kotlinx.coroutines.launch

class UserDetailsViewModel @ViewModelInject
constructor(private val userUsecase: UserDetailUsecase) : ViewModel() {

    val data: ObservableField<GitUser> = ObservableField()

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun fetchUserDetails(loginName: String) {
        _dataLoading.value = true
        viewModelScope.launch {
            try {
                val userDetails = userUsecase.invoke(loginName)
                data.set(userDetails)
            } catch (ex : Exception) {

            } finally {
                _dataLoading.value = false
            }
        }
    }

}