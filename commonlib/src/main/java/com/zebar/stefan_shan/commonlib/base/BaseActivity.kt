package com.zebar.shirt.baselib.base

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.ViewConfiguration
import com.zebar.stefan_shan.commonlib.R
import com.zebar.stefan_shan.commonlib.base.ActivityManager

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 基础Activity
 */
open class BaseActivity:AppCompatActivity() {
    @JvmField
    protected var swipeFinishEnable = true
    private var downX: Float = 0.toFloat()
    private var lastX: Float = 0.toFloat()
    private var swipeEnableThisTime = true
    private var edgeSlop: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //入场动画
        overridePendingTransition(R.anim.anim_in_from_right, R.anim.anim_out_to_left)
        //添加Activity
        ActivityManager.getInstance().addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        //退场动画
        overridePendingTransition(R.anim.anim_in_from_left, R.anim.anim_out_to_right)
        //移除Activity
        ActivityManager.getInstance().removeActivity(this)
    }

    /**
     * 左边缘向右滑动，结束Activity
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (!swipeFinishEnable) {
            return super.dispatchTouchEvent(ev)
        }

        if (edgeSlop == 0) {
            val vc = ViewConfiguration.get(this)
            edgeSlop = vc.scaledEdgeSlop
        }

        if (ev.action == MotionEvent.ACTION_DOWN) {
            downX = ev.x
            lastX = downX
            swipeEnableThisTime = downX < edgeSlop
        } else if (swipeEnableThisTime && ev.action == MotionEvent.ACTION_MOVE) {
            if (ev.x < lastX) {
                swipeEnableThisTime = false
            }

            lastX = ev.x
        } else if (swipeEnableThisTime && ev.action == MotionEvent.ACTION_UP) {
            val moveDistanceX = ev.x - downX
            if (moveDistanceX > 200) {
                finish()
                val now = SystemClock.uptimeMillis()
                super.dispatchTouchEvent(MotionEvent.obtain(now, now, MotionEvent.ACTION_CANCEL, 0.0f, 0.0f, 0))
            }
        }

        return super.dispatchTouchEvent(ev)
    }
}