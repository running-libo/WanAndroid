package com.libo.base.api

import com.libo.base.BuildConfig

/**
 * create by libo
 * create on 2021/6/28
 * description 网络请求地址
 */
interface Api {

    companion object {

        /** 开发服务器地址  */
        private const val SERVER_ADDRESS_DEV = "https://www.wanandroid.com/"

        /** 测试服务器地址  */
        private const val SERVER_ADDRESS_BETA = "https://www.wanandroid.com/"

        /** 正式服务器地址  */
        private const val SERVER_ADDRESS_RELEASE = "https://www.wanandroid.com/"

        /**
         * 获取当前环境的域名
         */
        fun getBaseUrl(): String {
            return when (BuildConfig.NET_TYPE) {
                BuildConfig.IS_DEV -> {
                    SERVER_ADDRESS_DEV
                }
                BuildConfig.IS_BETA -> {
                    SERVER_ADDRESS_BETA
                }
                BuildConfig.IS_RELEASE -> {
                    SERVER_ADDRESS_RELEASE
                }
                else -> SERVER_ADDRESS_DEV
            }
        }

        /**
         * 置顶数据
         */
        const val TOP_DATA = "article/top/json"

        /**
         * 首页
         */
        const val HOME_PAGE = "article/list/{page}/json"

        /**
         * banner
         */
        const val BANNER = "banner/json"

        /**
         * 问答
         */
        const val QUESTION = "wenda/list/{page}/json"

        /**
         * 体系
         */
        const val SYSTEM = "tree/json"

    }

}