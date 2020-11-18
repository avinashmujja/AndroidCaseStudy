package com.android.sbcasestudy.user.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.sbcasestudy.R
import com.android.sbcasestudy.base.common.Callback
import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.databinding.ActivityUserBinding
import com.android.sbcasestudy.user.vm.UserViewModel
import com.android.sbcasestudy.userDetails.view.UserDetailsActivity
import com.android.sbcasestudy.utils.Utils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UserActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: ActivityUserBinding

    var gitUser: GitUser? = null

    private val userViewModel : UserViewModel by lazy {
        viewModelFactory.create(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        viewDataBinding.lifecycleOwner = this
        intent?.let {
            gitUser = intent.getParcelableExtra(Utils.USER)
            gitUser?.let {
                userViewModel.data.set(it)
                userViewModel.fetchUserDetails(it?.login)
            }
        }
        viewDataBinding.vm = userViewModel

        viewDataBinding.callback = object : Callback {
            override fun invokeUserDetails(gitUser: GitUser, selection: Int) {
                val intent = Intent(
                    this@UserActivity,
                    UserDetailsActivity::class.java
                ).apply {
                    putExtra(Utils.USER, gitUser)
                    putExtra(Utils.SELECTION, selection)
                }
                when(selection) {
                    Utils.FOLLOWING -> {
                        gitUser.followers?.let {
                            if(it > 0) {
                                startActivity(intent)
                            }
                        }
                    }
                    Utils.FOLLOWERS -> {
                        gitUser.following?.let {
                            if(it > 0) {
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}