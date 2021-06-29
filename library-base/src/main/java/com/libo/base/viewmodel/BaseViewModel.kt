package com.libo.base.viewmodel

import androidx.lifecycle.ViewModel
import com.libo.base.livedata.BaseLiveData

/**
 * create by libo
 * create on 2021/6/27
 * description 项目所有viewModel基类
 */
open class BaseViewModel : ViewModel() {
    var baseLiveData = BaseLiveData()

}