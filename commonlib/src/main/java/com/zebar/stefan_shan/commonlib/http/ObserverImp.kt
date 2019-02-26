package com.zebar.stefan_shan.commonlib.http

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author: Stefan_Shan
 * @data: 2018/8/31
 * @desc: 数据预处理类
 */
abstract class ObserverImp<T>(private val tag: Any) : Observer<T> {

    override fun onSubscribe(d: Disposable) {
        //添加订阅关系
        SubscribeManager.addSubscribe(tag, d)
        onStart()
    }

    override fun onNext(t:T) {
        //请求成功处理
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        when (e) {
            is UnknownHostException, is ConnectException -> onNoNet("网络不给力~")
            is SocketTimeoutException -> onError("请求超时")
            else -> onError(if (e.message == null) "网络异常" else e.message.toString())
        }
    }

    override fun onComplete() {
        onAfter()
    }

    fun onStart() {}
    abstract fun onSuccess(t: T)
    abstract fun onError(message: String)
    abstract fun onNoNet(message: String)
    fun onAfter() {}
}