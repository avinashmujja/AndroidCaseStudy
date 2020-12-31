package com.android.ghcasestudy.userStats.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.ghcasestudy.R
import com.android.ghcasestudy.common.UsersCommonAdapter
import com.android.ghcasestudy.common.itemClick
import com.android.ghcasestudy.data.entities.GitUser
import com.android.ghcasestudy.databinding.ActivityUsersDetailsBinding
import com.android.ghcasestudy.userStats.vm.UserStatsViewModel
import com.android.ghcasestudy.utils.Utils

class UserStatsActivity : AppCompatActivity(),
    itemClick {

    private lateinit var viewDataBinding: ActivityUsersDetailsBinding

    lateinit var adapter: UsersCommonAdapter

    var selection: Int = 0

    var gitUser: GitUser? = null

    private val userDetailsViewModel: UserStatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_users_details)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewmodel = userDetailsViewModel
        initData()
        initRecyclerView()
    }

    private fun initData() {
        intent?.let {
            gitUser = intent.getParcelableExtra(Utils.USER)
            selection = intent.getIntExtra(Utils.SELECTION,0)

            viewDataBinding.tvFollow.text = when(selection) {
                Utils.FOLLOWING ->
                    "Followers"
                Utils.FOLLOWERS ->
                    "Following"
                else ->
                    ""
            }

            gitUser?.let {
                viewDataBinding.user = it
                it.login?.let { it ->
                    userDetailsViewModel.fetchUserDetails(it,selection)
                }
            }
        }
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
        /*val intent = Intent(this, UserActivity::class.java)
        intent.apply {
            putExtra(Utils.USER,gitUser)
        }
        startActivity(intent)*/
    }
}