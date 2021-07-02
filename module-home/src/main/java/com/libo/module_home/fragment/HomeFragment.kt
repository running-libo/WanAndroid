package com.libo.module_home.fragment

import androidx.viewpager2.widget.ViewPager2
import com.libo.base.fragment.BaseMvvmFragment
import com.libo.module_home.R
import com.libo.module_home.adpater.ViewPager2Adapter
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
        setBannerAdapter()
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

    private fun setBannerAdapter() {
        //设置滑动方向，可为横向和纵向
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //设置滑动事件监听
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)


            }
        })
        var datas = ArrayList<Int>()
        datas.add(R.mipmap.test_banner)
        datas.add(R.mipmap.test_banner)
        datas.add(R.mipmap.test_banner)

        //设置adapter
        binding.viewPager.adapter = ViewPager2Adapter(activity!!, datas)
    }
}