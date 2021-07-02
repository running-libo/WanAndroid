package com.libo.module_home.fragment

import androidx.viewpager2.widget.ViewPager2
import com.libo.base.fragment.BaseMvvmFragment
import com.libo.base.util.dip2pxInt
import com.libo.module_home.adpater.BannerAdapter
import com.libo.module_home.bean.BannerData
import com.libo.module_home.databinding.FragmentHomeBinding
import com.libo.module_home.viewmodel.HomeViewModel
import com.to.aboomy.pager2banner.ScaleInTransformer

/**
 * create by libo
 * create on 2021/6/28
 * description 主页fragment
 */

class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun initView() {
        setRefreshLoadMore()
        setBannerAdapter()
        onEvent()
    }

    private fun setRefreshLoadMore() {
        binding.smartRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
            viewModel.loadBanner()
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
        binding.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
        binding.viewPager.setAutoTurningTime(3000)
        binding.viewPager.startTurning()

        //设置左右页面露出来的宽度及item与item之间的宽度
        binding.viewPager.setPageMargin(activity!!.dip2pxInt(20f), activity!!.dip2pxInt(10f))
        //内置ScaleInTransformer，设置切换缩放动画
        binding.viewPager.addPageTransformer(ScaleInTransformer())
        var datas = ArrayList<BannerData>()

        //设置adapter
        binding.viewPager.adapter = BannerAdapter(activity!!, datas)
    }

    private fun onEvent() {
        viewModel.bannerData.observe(this, {
            (binding.viewPager.adapter as BannerAdapter).addDatas(it)
        })
    }
}