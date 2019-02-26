package com.zebar.stefan_shan.commonlib.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.*

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 基础Fragment
 */
abstract class BaseFragment : Fragment(){
    var rootView: View? = null
    var isReuse: Boolean = false
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater.inflate(inflateView(), container, false)
            isReuse = false
        } else {
            isReuse = true
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        if (savedInstanceState == null) {
            fitSystemWindows(true)
        }
    }

    @LayoutRes
    abstract fun inflateView(): Int

    abstract fun init(savedInstanceState: Bundle?)

    open fun fitSystemWindowsView(): View? = null

    override fun onHiddenChanged(hidden: Boolean) {
        fitSystemWindows(!hidden)
    }

    @CallSuper
    open fun fitSystemWindows(fitSystemWindows: Boolean) {
        val view = fitSystemWindowsView() ?: return
        view.fitsSystemWindows = fitSystemWindows
        ViewCompat.requestApplyInsets(view)
    }
}
