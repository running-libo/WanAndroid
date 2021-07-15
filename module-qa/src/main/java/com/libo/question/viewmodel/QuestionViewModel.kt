package com.libo.question.viewmodel

import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.libo.basemvvm.viewmodel.BasePageViewModel
import com.libo.library_network.response.dataConvert
import com.libo.question.R
import com.libo.question.bean.DataBean
import com.libo.question.net.IQaService
import kotlinx.coroutines.launch

/**
 * create by libo
 * create on 2021/6/28
 * description
 */
class QuestionViewModel: BasePageViewModel<DataBean>() {

    override fun getItemLayoutId() = kotlin.run {
        R.layout.item_question
    }

    init {
        refresh()
    }

    override fun requestData(page: Int) {
        baseLiveData.loading.value = 1
        viewModelScope.launch {
            try {
                var questionData = IQaService.invoke().getQuestionData(page).dataConvert()
                handleItemData(page, questionData.datas)
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