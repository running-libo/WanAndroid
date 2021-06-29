package com.libo.library_network.api

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

        fun getBaseUrl(): String {
            return SERVER_ADDRESS_DEV
        }

        /**
         * 首页
         */
        const val HOME_PAGE = "article/list/{page}/json"

        /**
         * 问答
         */
        const val QUESTION = "wenda/list/{page}/json"
    }

}