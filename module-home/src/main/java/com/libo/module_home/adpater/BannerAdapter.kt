package com.libo.module_home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.libo.module_home.R
import com.libo.module_home.bean.BannerData

/**
 * create by apple
 * create on 2021/7/2
 * description
 */
class BannerAdapter(val context: Context, val datas: ArrayList<BannerData>): RecyclerView.Adapter<BannerAdapter.ViewHolder>() {
    var layoutInflater: LayoutInflater? = null

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivCover: ImageView? = null
        init {
            ivCover = itemView.findViewById(R.id.ivCover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = layoutInflater!!.inflate(R.layout.item_banner, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(datas[position].imagePath).into(holder.ivCover!!)

        holder.ivCover!!.setOnClickListener {
            ARouter.getInstance().build("/article/webpage")
                .withString("url", datas[position].url)
                .navigation()
        }
    }

    override fun getItemCount() = datas.size

    fun addDatas(datas: List<BannerData>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

}