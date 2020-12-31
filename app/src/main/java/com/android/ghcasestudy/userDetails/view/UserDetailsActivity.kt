package com.android.ghcasestudy.userDetails.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.ghcasestudy.R
import com.android.ghcasestudy.common.Callback
import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.databinding.ActivityUserBinding
import com.android.ghcasestudy.userDetails.vm.UserDetailsViewModel
import com.android.ghcasestudy.userStats.view.UserStatsActivity
import com.android.ghcasestudy.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {

    private lateinit var viewDataBinding: ActivityUserBinding

    var gitUser: GitUser? = null

    private val userViewModel: UserDetailsViewModel by viewModels()

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
                    this@UserDetailsActivity,
                    UserStatsActivity::class.java
                ).apply {
                    putExtra(Utils.USER, gitUser)
                    putExtra(Utils.SELECTION, selection)
                }
                when(selection) {
                    Utils.FOLLOWING -> {
                        gitUser.followers?.let {
                                startActivity(intent)
                        }
                    }
                    Utils.FOLLOWERS -> {
                        gitUser.following?.let {
                                startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}