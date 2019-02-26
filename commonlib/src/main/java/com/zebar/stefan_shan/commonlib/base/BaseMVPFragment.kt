package com.zebar.stefan_shan.commonlib.base

import android.os.Bundle

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 基础 MVP Fragment
 */
abstract class BaseMVPFragment<V, T : BasePresenter<V>> : BaseFragment() {
    protected var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
        if (mPresenter != null) {
            mPresenter!!.attachView(this as V)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
    }

    /**
     * 创建Presenter
     */
    protected abstract fun createPresenter(): T

}