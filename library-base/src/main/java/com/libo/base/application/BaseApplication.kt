package com.libo.base.application

import android.app.Application
import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.libo.base.BuildConfig
import com.libo.base.R
import com.libo.base.util.LogUtils.TAG
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter

/**
 * create by libo
 * create on 2021/6/27
 * description
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "onCreate")

        initArouter()
        initSmartRefreshLayout()
    }

    companion object {
        var instance: Application? = null
    }

    private fun initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    private fun initSmartRefreshLayout() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(fun(
            context: Context,
            layout: RefreshLayout
        ): RefreshHeader {
            layout.setEnableHeaderTranslationContent(true)
            return MaterialHeader(context).setColorSchemeResources(
                R.color.theme_color,
                R.color.theme_color,
                R.color.theme_color
            )
        })

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            return@setDefaultRefreshFooterCreator ClassicsFooter(context)
        }
    }

    override fun onTerminate() {
        // 程序终止的时候执行
        Log.d(TAG, "onTerminate")
        super.onTerminate()
    }

    override fun onLowMemory() {
        // 低内存的时候执行
        Log.d(TAG, "onLowMemory")
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        // 程序在内存清理的时候执行（回收内存）
        // HOME键退出应用程序、长按MENU键，打开Recent TASK都会执行
        Log.d(TAG, "onTrimMemory")
        super.onTrimMemory(level)
    }

}