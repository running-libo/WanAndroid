package com.libo.library_network.api

import com.libo.library_network.cacheconfig.DirConfig
import com.libo.library_network.interceptor.LogInterceptor
import com.libo.library_network.interceptor.NetCacheInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * create by libo
 * create on 2021/6/28
 * description 单例的Retrofit和Okhttp管理类
 */
object ApiManager {
    /** 链接超时时间 */
    private val TIMEOUT = 10

    private val mOkhttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)  //连接超时设置
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)   //读取缓存超时10s
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)  //写入缓存超时10s
            .retryOnConnectionFailure(true)  //失败重连
            .addInterceptor(NetCacheInterceptor())
            .addInterceptor(LogInterceptor())
            setCacheFile(builder)
            builder.build()
    }

    val retrofitBuilder by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkhttpClient)
    }

    fun <T> create(cls: Class<T>, baseUrl: String): T {
        return retrofitBuilder.baseUrl(baseUrl).build().create(cls)
    }

    /**
     * 设置缓存文件路径
     */
    private fun setCacheFile(builder: OkHttpClient.Builder) {
        //设置缓存文件
        val cacheFile = File(DirConfig.HTTP_CACHE)
        //缓存大小为10M
        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(cacheFile, cacheSize.toLong())
        builder.cache(cache)
    }
}