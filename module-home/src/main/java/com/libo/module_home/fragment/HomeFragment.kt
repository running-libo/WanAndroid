package com.libo.module_home.fragment

import androidx.lifecycle.Observer
import com.libo.base.fragment.BaseMvvmFragment
import com.libo.module_home.databinding.FragmentHomeBinding
import com.libo.module_home.viewmodel.HomeViewModel

/**
 * create by libo
 * create on 2021/6/28
 * description 主页fragment
 */

class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun initView() {
        setRefreshLoadMore()
    }

    private fun setRefreshLoadMore() {
        binding.smartRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        binding.smartRefreshLayout.setOnLoadMoreListener {
            viewModel.loadMore()
        }

        viewModel.baseLiveData.refresh.observe(this, {
            binding.smartRefreshLayout.finishRefresh()
        })

        viewModel.baseLiveData.loadMore.observe(this, {
            binding.smartRefreshLayout.finishLoadMore()
        })

        viewModel.baseLiveData.loadFail.observe(this, {
            binding.smartRefreshLayout.finishRefresh()
            binding.smartRefreshLayout.finishLoadMore()
        })
    }
}