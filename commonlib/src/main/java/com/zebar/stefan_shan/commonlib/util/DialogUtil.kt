package com.zebar.stefan_shan.commonlib.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.DialogFragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: Dialog工具类
 */
object DialogUtil {

    /**
     * 显示 DialogFragment
     * @param gravity 位置
     * @param width 宽度(dp) 默认wrap_content
     * @param height 高度(dp) 默认wrap_content
     * @param cancel 是否点击取消
     * @param initView 自定义样式中控件逻辑
     */
    fun showDialogFragment(
        @LayoutRes layoutId: Int, gravity: Int? = null,
        width: Int? = null,
        height: Int? = null,
        cancel: Boolean? = true,
        initView: ((view: View, dialog: DialogFragment) -> Unit)
    ) {
        DialogFragmentHolder.builder.setDialogView(layoutId)
            .setInitDialogView { view, dialog -> initView.invoke(view, dialog) }
            .setGravity(gravity)
            .setWidth(width)
            .setHeight(height)
            .setCancel(cancel)

    }
}

class DialogFragmentHolder : DialogFragment() {
    private var layoutId: Int = 0
    private var gravity: Int = 0
    private var width: Int = 0
    private var height: Int = 0

    companion object {
        val builder: DialogFragmentHolder
            get() = DialogFragmentHolder()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container)
        initDialog.invoke(view, this)
        return view
    }

    /**
     * 设置位置
     */
    override fun onStart() {
        super.onStart()
        val window = dialog.window
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val wlp = window.attributes
        wlp.gravity = gravity
        wlp.width = width
        wlp.height = height
        window.attributes = wlp
    }

    /**
     * 视图
     *
     * @param layoutId
     * @return
     */
    fun setDialogView(@LayoutRes layoutId: Int): DialogFragmentHolder {
        this.layoutId = layoutId
        return this
    }

    /**
     * 设置是否点击消失
     *
     * @param cancele
     * @return
     */
    fun setCancel(cancel: Boolean?): DialogFragmentHolder {
        this.isCancelable = cancel ?: true
        return this
    }

    /**
     * 设置弹框位置
     *
     * @param gravity
     * @return
     */
    fun setGravity(gravity: Int?): DialogFragmentHolder {
        if (gravity == null || gravity <= 0) {
            this.gravity = Gravity.CENTER
        } else {
            this.gravity = gravity
        }
        return this
    }

    /**
     * 设置宽度
     *
     * @param width
     * @return
     */
    fun setWidth(width: Int?): DialogFragmentHolder {
        if (width == null) {
            this.width = ViewGroup.LayoutParams.WRAP_CONTENT
        } else if (width == ViewGroup.LayoutParams.WRAP_CONTENT || width == ViewGroup.LayoutParams.MATCH_PARENT) {
            this.width = width
        } else {
            this.width = width.dp2px()
        }
        return this
    }

    /**
     * 设置高度
     *
     * @param height
     * @return
     */
    fun setHeight(height: Int?): DialogFragmentHolder {
        if (height == null) {
            this.height = ViewGroup.LayoutParams.WRAP_CONTENT
        } else if (height == ViewGroup.LayoutParams.WRAP_CONTENT || height == ViewGroup.LayoutParams.MATCH_PARENT) {
            this.height = height
        } else {
            this.height = height.dp2px()
        }
        return this
    }

    private lateinit var initDialog: ((view: View, dialog: DialogFragmentHolder) -> Unit)

    fun setInitDialogView(initDialogView: (view: View, dialog: DialogFragmentHolder) -> Unit): DialogFragmentHolder {
        this.initDialog = initDialogView
        return this
    }
}