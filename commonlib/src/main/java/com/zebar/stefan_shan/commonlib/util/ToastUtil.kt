package com.zebar.stefan_shan.commonlib.util

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.zebar.stefan_shan.commonlib.BaseApplication

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: Toast管理类
 */
object ToastUtil {

    private var mToast: Toast? = null

    //显示文字
    fun showToast(message: String?) {
        if (TextUtils.isEmpty(message)) {
            return
        }

        mToast?.cancel()
        mToast = Toast.makeText(BaseApplication.application, message, Toast.LENGTH_SHORT)
        mToast?.show()
    }

    //显示 strings
    fun showToast(@StringRes message: Int) {
        mToast?.cancel()
        mToast = Toast.makeText(BaseApplication.application, message, Toast.LENGTH_SHORT)
        mToast?.show()
    }

    //显示自定义布局
    fun showToast(context: Context, @LayoutRes customView: Int, initView: ((view: View) -> Unit)) {
        val toast = Toast(context)
        val toastView = View.inflate(context, customView, null)
        initView.invoke(toastView)
        toast.view = toastView
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        toast.show()
    }

}
