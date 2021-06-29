package com.libo.base.livedata

import androidx.lifecycle.MutableLiveData

/**
 * create by libo
 * create on 2021/6/29
 * description 项目事件通知基类
 */
class BaseLiveData {

    /** 请求成功通知  */
    val loadSuccess by lazy { MutableLiveData<Int>() }

    /** 请求失败通知  */
    val loadFail by lazy { MutableLiveData<Int>() }

    /** 开始loading */
    val loading by lazy { MutableLiveData<Int>() }

    /** 刷新 */
    val refresh by lazy { MutableLiveData<Int>() }

    /** 加载更多 */
    val loadMore by lazy { MutableLiveData<Int>() }

    /** 切换到空布局 */
    val switchToEmpty by lazy { MutableLiveData<Int>() }

    /** 切换到列表布局 */
    val switchToList by lazy { MutableLiveData<Int>() }
}