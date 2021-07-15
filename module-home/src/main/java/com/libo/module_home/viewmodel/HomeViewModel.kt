package com.libo.module_home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.libo.basemvvm.viewmodel.BasePageViewModel
import com.libo.library_network.response.dataConvert
import com.libo.module_home.R
import com.libo.module_home.bean.BannerData
import com.libo.module_home.bean.DataX
import com.libo.module_home.net.IHomeService
import kotlinx.coroutines.launch

/**
 * create by libo
 * create on 2021/6/28
 * description
 */
class HomeViewModel: BasePageViewModel<DataX>() {
    var bannerData = MutableLiveData<List<BannerData>>()

    override fun getItemLayoutId() = kotlin.run {
        R.layout.item_article_list
    }

    init {
        refresh()
        loadBanner()
    }

    override fun requestData(page: Int) {
        baseLiveData.loading.value = 1
        viewModelScope.launch {
            try {
                var homePageData = IHomeService.invoke().getHomePageData(page).dataConvert()
                handleItemData(page, homePageData.datas)

                if (page == 0) {  //置顶添加置顶数据
                    var topData = IHomeService.invoke().getTopData().dataConvert()
                    topData.map {
                        it.isTop = true
                    }
                    items.addAll(0, topData)
                }
            } catch (e: Exception) {
                baseLiveData.loadFail.value = 1
            } finally {

            }
        }
    }

    fun loadBanner() {
        viewModelScope.launch {
            try {
                bannerData.value = IHomeService.invoke().getBanner().dataConvert()
            } catch (e: Exception) {
                baseLiveData.loadFail.value = 1
            } finally {

            }
        }
    }

    fun onItemClick(url: String) {
        ARouter.getInstance().build("/article/webpage")
            .withString("url", url)
            .navigation()
    }
}