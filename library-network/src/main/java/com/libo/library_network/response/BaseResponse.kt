package com.libo.library_network.response

import java.io.Serializable

/**
 * create by libo
 * create on 2021/6/28
 * description 请求响应基类
 */
open class BaseResponse<T> (var errorCode: Int = 0, var errorMsg: String = "", var data: T): Serializable