package com.android.sbcasestudy.userDetails.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sbcasestudy.R
import com.android.sbcasestudy.base.common.UsersCommonAdapter
import com.android.sbcasestudy.base.common.itemClick
import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.databinding.ActivityUsersDetailsBinding
import com.android.sbcasestudy.user.view.UserActivity
import com.android.sbcasestudy.userDetails.vm.UserDetailsViewModel
import com.android.sbcasestudy.utils.Utils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UserDetailsActivity : DaggerAppCompatActivity() ,
    itemClick {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: ActivityUsersDetailsBinding

    lateinit var adapter: UsersCommonAdapter

    var selection : Int = 0

    var gitUser : GitUser? = null

    private val userDetailsViewModel : UserDetailsViewModel by lazy {
        viewModelFactory.create(UserDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_users_details)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewmodel = userDetailsViewModel
        intent?.let {
            gitUser = intent.getParcelableExtra(Utils.USER)
            selection = intent.getIntExtra(Utils.SELECTION,0)
            gitUser?.let {
                it.login?.let { it ->
                    userDetailsViewModel.fetchUserDetails(it,selection)
                }
            }
        }

        initRecyclerView()
    }
    private fun initRecyclerView() {
        viewDataBinding?.let {
            val viewModel = viewDataBinding.viewmodel
            adapter =
                UsersCommonAdapter(this)
            viewDataBinding.userList.adapter = adapter
        }

        viewDataBinding.userList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1) {
                    gitUser?.let {
                        it.login?.let { it ->
                            userDetailsViewModel.fetchNextBatchUserUserDetails(it,selection)
                        }
                    }
                }
            }
        })

    }

    override fun onItemClick(gitUser: GitUser) {
        val intent = Intent(this, UserActivity::class.java)
        intent.apply {
            putExtra(Utils.USER,gitUser)
        }
        startActivity(intent)
    }
}