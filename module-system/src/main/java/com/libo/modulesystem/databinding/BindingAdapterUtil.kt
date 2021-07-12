package com.libo.modulesystem.databinding

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.libo.flowlayout.FlowLayoutView
import com.libo.modulesystem.Children
import com.libo.modulesystem.R

/**
 * create by apple
 * create on 2021/7/3
 * description
 */
object BindingAdapterUtil {

    /**
     * flowlayout添加数据
     */
    @JvmStatic
    @BindingAdapter(value = ["flowTags"], requireAll = false)
    fun flowTags(flowLayoutView: FlowLayoutView, datas: List<Children>) {
        flowLayoutView.removeAllViews()

        datas.forEach {
            val view: View = LayoutInflater.from(flowLayoutView.context).inflate(R.layout.item_tagview, null)
            var textView = view.findViewById<TextView>(R.id.tv_content)
            textView.apply {
                text = it.name
                flowLayoutView.addView(view)
            }
        }
    }
}