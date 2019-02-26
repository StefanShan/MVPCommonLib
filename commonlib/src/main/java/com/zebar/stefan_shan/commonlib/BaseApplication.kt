package com.zebar.stefan_shan.commonlib

import android.app.Application
import android.content.pm.ApplicationInfo

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 基础 Application
 */
class BaseApplication: Application() {
    companion object {
        lateinit var application: BaseApplication
        var isDebug:Boolean = false
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        isDebug = applicationInfo != null && (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
    }
}