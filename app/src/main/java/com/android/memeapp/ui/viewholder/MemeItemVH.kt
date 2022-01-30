package com.android.memeapp.ui.viewholder

import com.android.memeapp.R
import com.android.memeapp.base.BaseViewHolder
import com.android.memeapp.constant.AppConstant
import com.android.memeapp.databinding.MemeItemLayoutBinding
import com.android.memeapp.db.entity.MemeObject

class MemeItemVH(private val binder: MemeItemLayoutBinding) :
    BaseViewHolder<MemeObject>(binder.root) {
    override fun bind(data: MemeObject?) {
        binder.title = data?.name
        binder.url = data?.url
        binder.placeholder = R.drawable.ic_placeholder
        binder.imageAspectRatio = filterAspectRatio(data)
        binder.executePendingBindings()
    }

    fun filterAspectRatio(data: MemeObject?): String {
        val width = data?.width?.toInt() ?: -1
        val height = data?.height?.toInt() ?: -1
        if (width < 1 || height < 1) {
            return AppConstant.DEFAULT_IMAGE_ASPECT_RATIO
        }
        return "$width:$height"
    }

}