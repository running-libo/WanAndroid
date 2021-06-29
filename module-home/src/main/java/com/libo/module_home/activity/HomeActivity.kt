package com.libo.module_home.activity

import android.Manifest
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.libo.base.activity.BaseActivity
import com.libo.module_home.R
import com.libo.module_home.databinding.ActivityHomeBinding
import com.libo.module_home.fragment.HomeFragment
import com.tbruyelle.rxpermissions.RxPermissions

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

    private var mFragments = ArrayList<Fragment>()

    private lateinit var viewBinding: ActivityHomeBinding

    override fun setLayoutId(): View = viewBinding.root

    var rxPermission: RxPermissions? = null


    override fun initViewBinding(layoutInflater: LayoutInflater) {
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initView() {

        rxPermission = RxPermissions(this)
        rxPermission!!.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            .subscribe {
                if (it) {
                    initFragments()
                    initNavigationView()
                } else {
                    Toast.makeText(applicationContext, "未授权权限，部分功能不能使用", Toast.LENGTH_SHORT).show();
                }
            }
    }

    private fun initNavigationView() {
        with(viewBinding.navigationView.menu) {
            add(0, 0, 0, "首页")
            add(0, 1, 1, "问答")
            findItem(0).setIcon(R.mipmap.ic_home_pressed)
            findItem(1).setIcon(R.mipmap.ic_question_pressed)
        }

        viewBinding.navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                0 -> {
                    supportFragmentManager.beginTransaction().show(mFragments[0]).commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(mFragments[1]).commitAllowingStateLoss()
                }
                1 -> {
                    supportFragmentManager.beginTransaction().show(mFragments[1]).commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(mFragments[0]).commitAllowingStateLoss()
                }
            }
            true
        }
    }

    private fun initFragments() {
        var homeFragment = HomeFragment()
        var questionFragment = ARouter.getInstance().build("/qa/question").navigation()
        mFragments.add(homeFragment)
        mFragments.add(questionFragment as Fragment)

        //默认选中第一个
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.flContainer, homeFragment)
        transaction.add(R.id.flContainer, questionFragment)
        transaction.hide(questionFragment)
        transaction.commit()
    }

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