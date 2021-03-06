package com.libo.modulesystem.net

import com.libo.base.api.Api
import com.libo.library_network.api.ApiManager
import com.libo.library_network.response.BaseResponse
import com.libo.modulesystem.SystemData
import retrofit2.http.GET

/**
 * create by libo
 * create on 2021/6/29
 * description
 */
interface ISystemService {

    companion object {
        val instance = ApiManager.create(ISystemService::class.java, Api.getBaseUrl())

        fun invoke(): ISystemService {
            return instance
        }
    }

    @GET(Api.SYSTEM)
    suspend fun getSystemData(): BaseResponse<List<SystemData>>
}