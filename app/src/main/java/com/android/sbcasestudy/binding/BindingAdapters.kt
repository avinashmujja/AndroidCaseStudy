package com.android.sbcasestudy.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.sbcasestudy.base.common.UsersCommonAdapter
import com.android.sbcasestudy.data.GitUser
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {
    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(listView: RecyclerView, items:MutableList<GitUser>?) {
        (listView.adapter as UsersCommonAdapter).submitList(items?.toList())
    }

    @BindingAdapter("visibleGone")
    @JvmStatic
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun showImage(view: ImageView, imagUrl: String?) {
        imagUrl?.let {
            Glide.with(view.context)
                .load(imagUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(view)
        }
    }

    @BindingAdapter("text","name")
    @JvmStatic
    fun showText(view:TextView,count:String,name:String){
        view.text = "$count $name"
    }
}