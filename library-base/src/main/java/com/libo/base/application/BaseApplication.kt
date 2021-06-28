package com.libo.base.application

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.libo.base.BuildConfig

/**
 * create by libo
 * create on 2021/6/27
 * description
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initArouter()
    }

    private fun initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}