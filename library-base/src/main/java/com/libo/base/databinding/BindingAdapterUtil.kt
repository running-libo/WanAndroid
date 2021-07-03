package com.libo.base.databinding

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.libo.base.R
import com.libo.base.widget.FlowLayoutView

/**
 * create by libo
 * create on 2021/7/3
 * description
 */
object BindingAdapterUtil {

    @JvmStatic
    @BindingAdapter(value = ["imgUrl"], requireAll = true)
    fun setImgUrl(imageView: ImageView, url: String?) {

        if (!TextUtils.isEmpty(url)) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }

    /**
     * 给view设置点击跳转页面
     */
    @JvmStatic
    @BindingAdapter(value = ["jumpPage"], requireAll = false)
    fun jumpPage(view: View, routePath: String) {
        view.setOnClickListener {
            ARouter.getInstance().build(routePath).navigation()
        }
    }
}