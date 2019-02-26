package com.zebar.stefan_shan.commonlib.base

import android.os.Bundle
import com.zebar.shirt.baselib.base.BaseActivity

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 基础 MVP Activity
 */
abstract class BaseMVPActivity<V, T : BasePresenter<V>> : BaseActivity() {
    protected var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = createPresenter()
        if (mPresenter != null) {
            mPresenter!!.attachView(this as V)
        }
        setContentView(getContentView())
        initView()
    }

    /**
     * 设置布局文件
     */
    protected abstract fun getContentView(): Int

    /**
     * 初始化布局控件
     */
    protected abstract fun initView()

    /**
     * 创建 Presenter
     */
    protected abstract fun createPresenter(): T


    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
    }
}