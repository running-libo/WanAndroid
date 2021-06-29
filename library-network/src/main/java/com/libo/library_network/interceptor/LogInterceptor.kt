package com.libo.library_network.interceptor

import com.libo.base.util.LogUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * create by libo
 * create on 2021/6/29
 * description 发送请求、返回数据日志拦截器
 */
class LogInterceptor: Interceptor {
    private val byteCount = 1024 * 1024 * 10

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        LogUtils.d(String.format("发送请求  %s", request.url()))

        val responseBody = response.peekBody(byteCount.toLong())
        LogUtils.d(String.format("接收响应  %s", responseBody.string()))

        return response
    }

}