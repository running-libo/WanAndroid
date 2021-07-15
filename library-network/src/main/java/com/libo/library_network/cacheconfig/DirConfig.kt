package com.libo.library_network.cacheconfig

import android.os.Environment

/**
 * create by libo
 * create on 2021/6/28
 * description 存储路径配置
 */
object DirConfig {
    /**
     * 请求数据缓存
     */
    val HTTP_CACHE = Environment.getExternalStorageDirectory().absolutePath + "/httpCache"
}