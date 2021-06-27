package com.libo.module_base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.gyf.immersionbar.ImmersionBar
import com.libo.module_base.util.ActivityManager

/**
 * create by libo
 * create on 2021/6/27
 * description BaseActivity封装
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(setLayoutId())
        statusBaySetting()
        ActivityManager.addActivity(this)
    }

    abstract fun setLayoutId(): Int

    /**
     * 系统状态栏颜色设置
     */
    open fun statusBaySetting() {
        //监听activity的onCreate生命周期事件
        lifecycle.addObserver(object: LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
            fun onCreate() {
                //onCreate生命周期事件回调
                ImmersionBar.with(this@BaseActivity)
                    .statusBarColor(setStatusBarColor())
                    .fitsSystemWindows(true)
                    .init()
            }
        })
    }

    open fun setStatusBarColor(): Int = com.libo.library_res.R.color.white

    override fun onDestroy() {
        super.onDestroy()

        ActivityManager.removeActivity(this)
    }
}