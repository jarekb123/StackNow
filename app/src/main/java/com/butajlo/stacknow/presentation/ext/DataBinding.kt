package com.butajlo.stacknow.presentation.ext

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

inline fun <reified T : ViewDataBinding> RecyclerView.ViewHolder.binding() =
    lazy { itemView.let { DataBindingUtil.bind<T>(it) } }

inline fun <reified T : ViewDataBinding> Fragment.binding() =
    lazy { view?.let { DataBindingUtil.bind<T>(it) } }

@BindingAdapter("android:text")
fun TextView.bindNumber(int: Int) {
    text = int.toString()
}
