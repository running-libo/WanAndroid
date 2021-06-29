package com.libo.module_home.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.libo.base.viewmodel.BaseViewModel
import com.libo.module_home.net.IHomeService
import kotlinx.coroutines.launch

/**
 * create by libo
 * create on 2021/6/28
 * description
 */
class HomeViewModel: BaseViewModel() {

    init {
        requestData()
    }

    private fun requestData() {
        viewModelScope.launch {
            val homePageData = IHomeService.invoke().getHomePageData()
            Log.i("minfo", homePageData.toString())
        }
    }
}