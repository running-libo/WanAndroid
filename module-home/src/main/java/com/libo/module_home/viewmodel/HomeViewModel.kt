package com.libo.module_home.viewmodel

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.viewModelScope
import com.libo.base.viewmodel.BaseViewModel
import com.libo.library_network.response.dataConvert
import com.libo.module_home.R
import com.libo.module_home.bean.DataX
import com.libo.module_home.net.IHomeService
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * create by libo
 * create on 2021/6/28
 * description
 */
class HomeViewModel: BaseViewModel() {
    var items = ObservableArrayList<DataX>()

    val itemBinding by lazy {
        ItemBinding.of<DataX>(BR.item, R.layout.item_article_list).bindExtra(BR.viewModel, this)
    }

    init {
        requestData()
    }

    private fun requestData() {
        viewModelScope.launch {
            var homePageData = IHomeService.invoke().getHomePageData().dataConvert()
            items.addAll(homePageData.datas)
        }
    }
}