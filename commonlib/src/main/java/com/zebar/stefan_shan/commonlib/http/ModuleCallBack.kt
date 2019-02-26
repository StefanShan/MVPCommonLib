package com.zebar.stefan_shan.commonlib.http

/**
 * @author: Stefan_Shan
 * @data: 2018/8/31
 * @desc: module回调接口
 */
interface ModuleCallBack<T> {
    //请求前
    fun onStart() {}

    //请求成功
    fun onSuccess(data: T)

    //请求报错
    fun onError(error: String)

    //网络异常
    fun onNoNet(msg: String)

    //请求后
    fun onAfter() {}
}