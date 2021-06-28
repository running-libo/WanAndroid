package com.libo.wanandroid

import android.view.View
import android.widget.Toast
import com.libo.base.activity.BaseActivity
import com.libo.wanandroid.databinding.ActivityHomeBinding

/**
 * create by libo
 * create on 2021/6/27
 * description 主页面
 */
class HomeActivity : BaseActivity() {
    /** 连续按返回键退出时间  */
    private val EXIT_TIME = 2000
    /** 上次点击返回键时间  */
    private var lastTime: Long = 0

    override fun setLayoutId(): View = viewBinding.root

    override fun initViewBinding() = ActivityHomeBinding.inflate(layoutInflater)

    /**
     * 双击返回退出App
     */
    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTime > EXIT_TIME) {
            Toast.makeText(applicationContext, R.string.exit_app, Toast.LENGTH_SHORT).show()
            lastTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
        }
    }

}