package com.android.ghcasestudy.userList.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.ghcasestudy.R
import com.android.ghcasestudy.common.UsersCommonAdapter
import com.android.ghcasestudy.common.itemClick
import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.databinding.ActivityUsersListBinding
import com.android.ghcasestudy.userDetails.view.UserDetailsActivity
import com.android.ghcasestudy.userList.vm.UserListViewModel
import com.android.ghcasestudy.utils.Utils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UserListActivity : DaggerAppCompatActivity(),
    itemClick {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: ActivityUsersListBinding

    lateinit var adapter: UsersCommonAdapter

    private val userListViewModel : UserListViewModel by lazy {
        viewModelFactory.create(UserListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_users_list)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewmodel = userListViewModel
        initRecyclerView()
    }

    private fun initRecyclerView() {
        viewDataBinding?.let {
            val viewModel = viewDataBinding.viewmodel
            adapter =
                UsersCommonAdapter(this)
            viewDataBinding.userList.adapter = adapter
        }

        userListViewModel.fetchUserProfiles()

        viewDataBinding.userList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == adapter.itemCount - 1) {
                    userListViewModel.fetchNextBatchUserProfiles()
                }
            }
        })

    }

    override fun onItemClick(gitUser: GitUser) {
        val intent = Intent(this, UserDetailsActivity::class.java).apply {
            putExtra(Utils.USER,gitUser)
        }
        startActivity(intent)
    }
}