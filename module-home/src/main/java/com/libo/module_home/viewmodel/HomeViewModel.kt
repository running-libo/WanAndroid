package com.libo.module_home.viewmodel

import androidx.lifecycle.viewModelScope
import com.libo.base.viewmodel.BasePageViewModel
import com.libo.library_network.response.dataConvert
import com.libo.module_home.R
import com.libo.module_home.bean.DataX
import com.libo.module_home.net.IHomeService
import kotlinx.coroutines.launch

/**
 * create by libo
 * create on 2021/6/28
 * description
 */
class HomeViewModel: BasePageViewModel<DataX>() {

    override fun getItemLayoutId() = kotlin.run {
        R.layout.item_article_list
    }

    init {
        refresh()
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
                error(e)
                baseLiveData.loadFail.value = 1
            } finally {

            }
        }
    }
}