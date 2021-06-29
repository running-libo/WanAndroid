package com.libo.module_home.net

import com.libo.library_network.api.Api
import com.libo.library_network.api.ApiManager
import com.libo.library_network.response.BaseResponse
import com.libo.module_home.bean.ArticleListData
import retrofit2.http.GET

/**
 * create by libo
 * create on 2021/6/28
 * description
 */
interface IHomeService {

    companion object {
        val instance = ApiManager.retrofit.create(IHomeService::class.java)

        fun invoke(): IHomeService {
            return instance
        }
    }

    /**
     * 首页
     */
    @GET(Api.HOME_PAGE)
    suspend fun getHomePageData(): BaseResponse<ArticleListData>
}