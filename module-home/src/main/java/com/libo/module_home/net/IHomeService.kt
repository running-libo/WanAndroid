package com.libo.module_home.net

import com.libo.base.api.Api
import com.libo.library_network.api.ApiManager
import com.libo.library_network.response.BaseResponse
import com.libo.module_home.bean.ArticleListData
import com.libo.module_home.bean.BannerData
import com.libo.module_home.bean.DataX
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * create by libo
 * create on 2021/6/28
 * description
 */
interface IHomeService {

    companion object {
        val instance = ApiManager.create(IHomeService::class.java, Api.getBaseUrl())

        fun invoke(): IHomeService {
            return instance
        }
    }

    /**
     * 置顶
     */
    @GET(Api.TOP_DATA)
    suspend fun getTopData(): BaseResponse<List<DataX>>

    /**
     * 首页
     */
    @GET(Api.HOME_PAGE)
    suspend fun getHomePageData(@Path("page") page: Int): BaseResponse<ArticleListData>

    /**
     * banner
     */
    @GET(Api.BANNER)
    suspend fun getBanner(): BaseResponse<List<BannerData>>
}