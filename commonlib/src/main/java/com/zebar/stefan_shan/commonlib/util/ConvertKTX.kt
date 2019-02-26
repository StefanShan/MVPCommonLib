package com.zebar.stefan_shan.commonlib.util

import com.zebar.stefan_shan.commonlib.BaseApplication
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 转换工具类
 */


// dp 转 px
fun Float.dp2px(): Int {
    val scale = BaseApplication.application.resources.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

// px 转 dp
fun Float.px2dp(): Int {
    val scale = BaseApplication.application.resources.displayMetrics.density
    return (this / scale + 0.5f).toInt()
}

// sp 转 px
fun Float.sp2px(): Float {
    val fontScale = BaseApplication.application.resources.displayMetrics.scaledDensity
    return this * fontScale + 0.5f
}

// dp 转 px (Int)
fun Int.dp2px(): Int {
    val scale = BaseApplication.application.resources.displayMetrics.density
    return (this * scale + 0.5f).toInt()
}

// px 转 dp (Int)
fun Int.px2dp(): Int {
    val scale = BaseApplication.application.resources.displayMetrics.density
    return (this / scale + 0.5f).toInt()
}

// sp 转 px (Int)
fun Int.sp2px(): Float {
    val fontScale = BaseApplication.application.resources.displayMetrics.scaledDensity
    return this * fontScale + 0.5f
}

//日期 转 星期
fun String.dateToWeek(): String {
    val f = SimpleDateFormat("yyyy-MM-dd")
    val weekDays = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
    val cal = Calendar.getInstance() // 获得一个日历
    var datet: Date? = null
    try {
        datet = f.parse(this)
        cal.time = datet!!
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    var w = cal.get(Calendar.DAY_OF_WEEK) - 1 // 指示一个星期中的某天。
    if (w < 0) {
        w = 0
    }
    return weekDays[w]
}

//字符串 转 时间戳
fun String.StringToDate(): Long {
    try {
        val simpleDateFormat = SimpleDateFormat("HH:mm:SS")
        val date = simpleDateFormat.parse(this)
        return date.time
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return 0
}

//日期 转 日期+星期
fun String.dateToDateWeek(): String? {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:SS")
    try {
        val date = simpleDateFormat.parse(this)
        val dateFormat = SimpleDateFormat("yyyy年MM月dd日 EEEE")
        return dateFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return null
}