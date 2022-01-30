package com.android.memeapp.base

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<Data>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: Data?)
}