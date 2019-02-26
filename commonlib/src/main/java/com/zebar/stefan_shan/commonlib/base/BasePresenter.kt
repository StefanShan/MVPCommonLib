package com.zebar.stefan_shan.commonlib.base

import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 基础Presenter
 */
open class BasePresenter<V> {

    var viewRef:Reference<V> ?= null

    /**
     * 绑定视图
     */
    fun attachView(view : V){
        viewRef = WeakReference<V>(view)
    }

    /**
     * 解绑视图
     */
    fun detachView(){
        if (viewRef != null){
            viewRef?.clear()
            viewRef = null
        }
    }

    /**
     * 获取当前视图
     */
    fun getView():V?{
        return viewRef?.get()
    }
}