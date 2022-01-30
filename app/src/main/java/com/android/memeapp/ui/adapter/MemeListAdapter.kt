package com.android.memeapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.memeapp.base.BaseViewHolder
import com.android.memeapp.databinding.MemeItemLayoutBinding
import com.android.memeapp.db.entity.MemeObject
import com.android.memeapp.ui.viewholder.MemeItemVH
import dagger.hilt.android.qualifiers.ActivityContext
import java.util.ArrayList
import javax.inject.Inject


class MemeListAdapter @Inject constructor(
    @ActivityContext private val ctx: Context
) : RecyclerView.Adapter<BaseViewHolder<MemeObject>>() {

    private var _memeList = arrayListOf<MemeObject>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MemeObject> {
        val binder=MemeItemLayoutBinding.inflate(LayoutInflater.from(ctx),parent,false)
    return MemeItemVH(binder)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<MemeObject>, position: Int) {
        holder.bind(_memeList.getOrNull(position))
    }

    override fun getItemCount(): Int = _memeList.size
    fun update(body: ArrayList<MemeObject>) {
        val oldSize = _memeList.size
        _memeList.addAll(body)
        notifyItemRangeInserted(oldSize, body.size)
    }

    fun clearContent() {
        _memeList.clear()
        notifyDataSetChanged()
    }


    val memeList: MutableList<MemeObject>
        get() = _memeList


}