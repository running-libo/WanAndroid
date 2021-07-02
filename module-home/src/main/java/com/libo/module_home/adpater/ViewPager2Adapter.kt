package com.libo.module_home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.libo.module_home.R

/**
 * create by apple
 * create on 2021/7/2
 * description
 */
class ViewPager2Adapter(val context: Context, val datas: List<Int>): RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {
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
        holder.ivCover?.background = context.resources.getDrawable(datas[position])
    }

    override fun getItemCount() = datas.size

}