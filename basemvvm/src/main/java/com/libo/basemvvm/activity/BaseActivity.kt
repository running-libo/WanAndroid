package com.libo.basemvvm.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * create by libo
 * create on 2021/6/27
 * description BaseActivity封装
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewBinding(layoutInflater)
        setContentView(setLayoutId())
        initView()
    }

    abstract fun setLayoutId(): View

    abstract fun initViewBinding(layoutInflater: LayoutInflater)

    open fun initView() {}

}