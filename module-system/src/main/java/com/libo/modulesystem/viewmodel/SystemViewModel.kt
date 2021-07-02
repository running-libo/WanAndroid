package com.libo.modulesystem.viewmodel

import androidx.lifecycle.viewModelScope
import com.libo.base.viewmodel.BasePageViewModel
import com.libo.library_network.response.dataConvert
import com.libo.modulesystem.R
import com.libo.question.net.ISystemService
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
                ISystemService.invoke().getSystemData().dataConvert()
//                var questionData = IQaService.invoke().getQuestionData(page).dataConvert()
//                handleItemData(page, questionData.datas)
            } catch (e: Exception) {
                error(e)
                baseLiveData.loadFail.value = 1
            } finally {

            }
        }
    }

}