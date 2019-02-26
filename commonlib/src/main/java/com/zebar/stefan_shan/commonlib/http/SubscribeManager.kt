package com.zebar.stefan_shan.commonlib.http

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 订阅关系管理类
 */
object SubscribeManager {

    private val maps = mutableMapOf<Any, Disposable>()

     fun <T> observe(observable: Observable<T>): Observable<T> {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun addSubscribe(tag:Any,disposable: Disposable){
        maps[tag] = disposable
    }

    fun unSubscribe(tag:Any){
        val disposable = maps[tag]
        if(disposable != null && !disposable.isDisposed) {
            disposable.dispose()
            maps.remove(tag)
        }
        RetrofitManager.getInstance().cancelTag(tag)
    }

    fun unSubscribeAll(){
        for ((k)in maps){
            unSubscribe(k)
        }
    }
}