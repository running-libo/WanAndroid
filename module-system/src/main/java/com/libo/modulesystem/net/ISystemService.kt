package com.libo.question.net

import com.libo.library_network.api.Api
import com.libo.library_network.api.ApiManager
import com.libo.library_network.response.BaseResponse
import retrofit2.http.GET

/**
 * create by libo
 * create on 2021/6/29
 * description
 */
interface ISystemService {

    companion object {
        val instance = ApiManager.retrofit.create(ISystemService::class.java)

        fun invoke(): ISystemService {
            return instance
        }
    }

    @GET(Api.SYSTEM)
    suspend fun getSystemData(): BaseResponse<Any>
}