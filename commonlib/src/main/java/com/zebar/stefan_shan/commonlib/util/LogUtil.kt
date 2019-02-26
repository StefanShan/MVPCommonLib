package com.zebar.stefan_shan.commonlib.util

import android.util.Log
import com.zebar.stefan_shan.commonlib.BaseApplication

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 日志管理类
 */
object LogUtil {
    fun e(tag:String,msg:String){
        if (BaseApplication.isDebug){
            Log.e(tag,msg)
        }
    }

    fun d(tag:String,msg:String){
        if (BaseApplication.isDebug){
            Log.d(tag,msg)
        }
    }

    fun i(tag:String,msg:String){
        if (BaseApplication.isDebug){
            Log.i(tag,msg)
        }
    }

    fun v(tag:String,msg:String){
        if (BaseApplication.isDebug){
            Log.v(tag,msg)
        }
    }

    fun w(tag:String,msg:String){
        if (BaseApplication.isDebug){
            Log.w(tag,msg)
        }
    }

}