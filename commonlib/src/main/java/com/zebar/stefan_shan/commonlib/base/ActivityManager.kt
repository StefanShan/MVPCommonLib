package com.zebar.stefan_shan.commonlib.base

import android.app.Activity
import java.util.*

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 应用程序Activity管理类
 */
class ActivityManager {

    companion object {
        fun getInstance(): ActivityManager {
            return ActivityManagerHodler.singleton
        }
    }

    private object ActivityManagerHodler{
        internal val singleton = ActivityManager()
    }

    private val activityStack by lazy {
        Stack<Activity>()
    }

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        activityStack.add(activity)
    }

    /**
     * 获取栈顶Activity
     */
    internal fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    /**
     * 结束栈顶Activity
     */
    fun finishActivity() {
        val activity = activityStack.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity) {
        activityStack.remove(activity)
        activity.finish()
    }

    /**
     * 移除指定Activity
     */
    fun removeActivity(activity: Activity) {
        activityStack.remove(activity)
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        activityStack
            .filter { it.javaClass == cls }
            .forEach { finishActivity(it) }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack.size
        while (i < size) {
            if (null != activityStack[i]) {
                activityStack[i].finish()
            }

            i++
        }

        activityStack.clear()
    }

}