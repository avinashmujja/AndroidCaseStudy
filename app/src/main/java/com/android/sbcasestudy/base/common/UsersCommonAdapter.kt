package com.android.sbcasestudy.base.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.sbcasestudy.data.GitUser
import com.android.sbcasestudy.databinding.RvItemUserBinding

class UsersCommonAdapter(click: itemClick) : ListAdapter<GitUser, UsersCommonAdapter.ViewHolder>(
    ItemDiffCallback()
) {

  var click: itemClick = click


    class ViewHolder private constructor(private val binding: RvItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GitUser) {
            binding.userItem = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvItemUserBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {click.onItemClick(item) }
    }

}

class ItemDiffCallback : DiffUtil.ItemCallback<GitUser>() {
    override fun areItemsTheSame(oldItem: GitUser, newItem: GitUser): Boolean {
        return oldItem.login== newItem.login
    }

    override fun areContentsTheSame(oldItem: GitUser, newItem: GitUser): Boolean {
        return oldItem == newItem
    }

}

interface itemClick {
    fun onItemClick(gitUser: GitUser)
}