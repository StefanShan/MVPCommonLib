package com.zebar.stefan_shan.commonlib.util

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.support.v4.app.Fragment

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: Activity跳转管理类
 */

//判断 Activity 是否已关闭
fun Activity?.isClosed(): Boolean {
    if (this == null) {
        return true
    }
    return isFinishing || isDestroyed
}

// Activity 跳转
inline fun <reified T> Activity.startActivity() = startActivity<T> {}

inline fun <reified T> Activity.startActivity(data: (intent: Intent) -> Unit) {
    val intent = Intent(this, T::class.java)
    data.invoke(intent)
    startActivity(intent)
}

inline fun <reified T> Activity.startActivityForResult(requestCode: Int) = startActivityForResult<T>(requestCode) {}

inline fun <reified T> Activity.startActivityForResult(requestCode: Int, data: (intent: Intent) -> Unit) {
    val intent = Intent(this, T::class.java)
    data.invoke(intent)
    startActivityForResult(intent, requestCode)
}

inline fun <reified T> Fragment.startActivity() = startActivity<T> {}

inline fun <reified T> Fragment.startActivity(data: (intent: Intent) -> Unit) = activity?.startActivity<T>(data)

inline fun <reified T> Fragment.startActivityForResult(requestCode: Int) = startActivityForResult<T>(requestCode) {}

inline fun <reified T> Fragment.startActivityForResult(requestCode: Int, data: (intent: Intent) -> Unit) {
    val intent = Intent(activity, T::class.java)
    data.invoke(intent)
    startActivityForResult(intent, requestCode)
}

inline fun <reified T> Application.startActivity() = startActivity<T> {}

inline fun <reified T> Application.startActivity(data: (intent: Intent) -> Unit) {
    val intent = Intent(this, T::class.java)
    data.invoke(intent)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}