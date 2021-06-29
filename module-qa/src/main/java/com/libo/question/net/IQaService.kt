package com.libo.question.net

import com.libo.library_network.api.Api
import com.libo.library_network.api.ApiManager
import com.libo.library_network.response.BaseResponse
import com.libo.question.bean.QuestionBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * create by libo
 * create on 2021/6/29
 * description
 */
interface IQaService {

    companion object {
        val instance = ApiManager.retrofit.create(IQaService::class.java)

        fun invoke(): IQaService {
            return instance
        }
    }

    @GET(Api.QUESTION)
    suspend fun getQuestionData(@Path("page") page: Int): BaseResponse<QuestionBean>
}