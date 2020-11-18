package com.android.sbcasestudy.usersList.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.sbcasestudy.R
import com.android.sbcasestudy.base.common.UsersCommonAdapter
import com.android.sbcasestudy.base.common.itemClick
import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.databinding.ActivityUsersListBinding
import com.android.sbcasestudy.user.view.UserActivity
import com.android.sbcasestudy.usersList.vm.UserListViewModel
import com.android.sbcasestudy.utils.Utils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class UsersListActivity : DaggerAppCompatActivity(),
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
        /*val intent = Intent(this, UserActivity::class.java).apply {
            putExtra(Utils.USER,gitUser)
        }
        startActivity(intent)*/
    }
}