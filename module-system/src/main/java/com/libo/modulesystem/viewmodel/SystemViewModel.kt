package com.libo.modulesystem.viewmodel

import androidx.lifecycle.viewModelScope
import com.libo.basemvvm.viewmodel.BasePageViewModel
import com.libo.library_network.response.dataConvert
import com.libo.modulesystem.R
import com.libo.modulesystem.net.ISystemService
import kotlinx.coroutines.launch

/**
 * create by libo
 * create on 2021/6/28
 * description
 */
class SystemViewModel: BasePageViewModel<Any>() {

    override fun getItemLayoutId() = kotlin.run {
        R.layout.item_system
    }

    init {
        refresh()
    }

    override fun requestData(page: Int) {
        baseLiveData.loading.value = 1
        viewModelScope.launch {
            try {
                var systemData = ISystemService.invoke().getSystemData().dataConvert()
                handleItemData(page, systemData)
            } catch (e: Exception) {
                baseLiveData.loadFail.value = 1
            } finally {

            }
        }
    }

}