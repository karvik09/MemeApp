package com.android.memeapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowMetrics
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.memeapp.R
import com.android.memeapp.base.InfiniteScrollListener
import com.android.memeapp.constant.AppConstant
import com.android.memeapp.constant.Fresh
import com.android.memeapp.constant.LoadType
import com.android.memeapp.constant.More
import com.android.memeapp.data.network.Failure
import com.android.memeapp.data.network.Loading
import com.android.memeapp.data.network.Success
import com.android.memeapp.databinding.ActivityMemeBinding
import com.android.memeapp.db.entity.MemeObject
import com.android.memeapp.ui.adapter.MemeListAdapter
import com.android.memeapp.viewmodel.MemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList
import javax.inject.Inject

@AndroidEntryPoint
class MemeActivity : AppCompatActivity() {

    @Inject
    lateinit var listAdapter: MemeListAdapter
    private lateinit var binder: ActivityMemeBinding

    //    @Inject
    private val viewModel: MemeViewModel by viewModels()

    private var loading = false
    private var hasMore = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = DataBindingUtil.setContentView(this, R.layout.activity_meme)
        initView()
        observe()
        viewModel.fetchMeme(Fresh)
    }

    private fun initView() {
        val gridSpan = resources.getInteger(R.integer.meme_grid_span)
        binder.memeRv.layoutManager =
            StaggeredGridLayoutManager(gridSpan, StaggeredGridLayoutManager.VERTICAL)
        binder.memeRv.addOnScrollListener(_onScrollCallback)
        binder.memeRv.adapter = listAdapter
        binder.retry.retryBtn.setOnClickListener {
            viewModel.fetchMeme(Fresh)
        }
        binder.refreshLayout.setOnRefreshListener {
            if (loading) {
                binder.refreshLayout.isRefreshing = false
                return@setOnRefreshListener
            }
            listAdapter.clearContent()
            viewModel.fetchMeme(Fresh)
        }
    }

    private val _onScrollCallback = object : InfiniteScrollListener() {
        override fun hasMoreItem(): Boolean {
            return hasMore
        }

        override fun isLoading(): Boolean {
            return loading
        }

        override fun onLoadMore() {
            loading = true
            val offset = listAdapter.memeList.size
            viewModel.fetchMeme(More(AppConstant.PAGE_SIZE, offset))
        }

    }

    private fun observe() {
        viewModel.fetchMemeLiveData.observe(this, {
            when (it) {
                is Loading -> {
                    handleLoading(it.loadType)
                }
                is Success -> {
                    handleSuccess(it.body)
                }
                is Failure -> {
                    handleFailure()
                }
            }
        })
    }

    private fun showProgress(show: Boolean) {
        binder.refreshLayout.isRefreshing = show
    }

    private fun handleFailure() {
        showProgress(false)
        if (listAdapter.memeList.isEmpty()) {
            binder.retry.root.isVisible = true
        }
        loading = false
    }

    private fun handleSuccess(body: ArrayList<MemeObject>?) {
        showProgress(false)
        if (body?.isNotEmpty() == true) {
            listAdapter.update(body)
        }
        if (listAdapter.memeList.isEmpty()) {
            binder.emptyView.isVisible = true
        }
        val lastListSize = body?.size ?: 0
        hasMore = lastListSize >= AppConstant.PAGE_SIZE
        loading = false

    }

    private fun handleLoading(loadType: LoadType) {
        loading = true
        if (loadType is Fresh) {
            hasMore = true
            showProgress(true)
            binder.emptyView.isVisible = false
            binder.retry.root.isVisible = false
        }
    }
}