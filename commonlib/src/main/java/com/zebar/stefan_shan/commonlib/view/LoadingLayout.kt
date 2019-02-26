package com.zebar.stefan_shan.commonlib.view

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.zebar.stefan_shan.commonlib.R

/**
 * @author: Stefan_Shan
 * @data: 2018/8/30
 * @desc: 加载状态工具类
 */
class LoadingLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs), View.OnClickListener {

    companion object {
        const val LOADING = 0
        const val SUCCESS = 1
        const val EMPTY = 2
        const val FAILED = 3
        const val NO_NET = 4
    }

    var state = SUCCESS
        set(value) {
            if (field == value) {
                return
            }

            field = value

            when (state) {
                LOADING -> {
                    if (layoutLoading == null) {
                        layoutLoading = LayoutInflater.from(context).inflate(resourceLoading, this, false)
                        addView(layoutLoading)

//                        if (resourceLoading == R.layout.loading_layout_loading) {
//                            Glide.with(context).load(R.drawable.icon_refresh).into(layoutLoading!!.findViewById(R.id.iv_loading))
//                        }
                    } else {
                        layoutLoading?.visibility = View.VISIBLE
                    }

                    layoutEmpty?.visibility = View.INVISIBLE
                    layoutFailed?.visibility = View.INVISIBLE
                    layoutNoNet?.visibility = View.INVISIBLE
                }
                SUCCESS -> {
                    layoutLoading?.visibility = View.INVISIBLE
                    layoutEmpty?.visibility = View.INVISIBLE
                    layoutFailed?.visibility = View.INVISIBLE
                    layoutNoNet?.visibility = View.INVISIBLE
                }
                EMPTY -> {
                    if (layoutEmpty == null) {
                        layoutEmpty = LayoutInflater.from(context).inflate(resourceEmpty, this, false)
                        addView(layoutEmpty)

                        if (retryIdEmpty != 0) {
                            layoutEmpty?.findViewById<View>(retryIdEmpty)?.setOnClickListener(this)
                        }
                    } else {
                        layoutEmpty?.visibility = View.VISIBLE
                    }

                    layoutLoading?.visibility = View.INVISIBLE
                    layoutFailed?.visibility = View.INVISIBLE
                    layoutNoNet?.visibility = View.INVISIBLE
                }
                FAILED -> {
                    if (layoutFailed == null) {
                        layoutFailed = LayoutInflater.from(context).inflate(resourceFailed, this, false)
                        addView(layoutFailed)

                        if (retryIdFailed != 0) {
                            layoutFailed?.findViewById<View>(retryIdFailed)?.setOnClickListener(this)
                        }
                    } else {
                        layoutFailed?.visibility = View.VISIBLE
                    }

                    layoutLoading?.visibility = View.INVISIBLE
                    layoutEmpty?.visibility = View.INVISIBLE
                    layoutNoNet?.visibility = View.INVISIBLE
                }
                NO_NET -> {
                    if (layoutNoNet == null) {
                        layoutNoNet = LayoutInflater.from(context).inflate(resourceNoNet, this, false)
                        addView(layoutNoNet)

                        if (retryIdNoNet != 0) {
                            layoutNoNet?.findViewById<View>(retryIdNoNet)?.setOnClickListener(this)
                        }
                    } else {
                        layoutNoNet?.visibility = View.VISIBLE
                    }

                    layoutLoading?.visibility = View.INVISIBLE
                    layoutEmpty?.visibility = View.INVISIBLE
                    layoutFailed?.visibility = View.INVISIBLE
                }
            }
        }

    private var onRetryListener: OnRetryListener? = null

    private var resourceLoading: Int = 0
    private var resourceEmpty: Int = 0
    private var resourceFailed: Int = 0
    private var resourceNoNet: Int = 0

    private var retryIdFailed: Int = 0
    private var retryIdEmpty: Int = 0
    private var retryIdNoNet: Int = 0

    private var layoutLoading: View? = null
    private var layoutEmpty: View? = null
    private var layoutFailed: View? = null
    private var layoutNoNet: View? = null

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingLayout, 0, 0)
        resourceLoading = ta.getResourceId(R.styleable.LoadingLayout_loadingLayout, R.layout.loading_layout_loading)
        resourceEmpty = ta.getResourceId(R.styleable.LoadingLayout_emptyLayout, R.layout.loading_layout_empty)
        resourceFailed = ta.getResourceId(R.styleable.LoadingLayout_failedLayout, R.layout.loading_layout_failed)
        resourceNoNet = ta.getResourceId(R.styleable.LoadingLayout_noNetLayout, R.layout.loading_layout_no_net)

        if (resourceEmpty == R.layout.loading_layout_empty) {
//            retryIdEmpty = R.id.ll_retry
        } else {
            retryIdEmpty = ta.getResourceId(R.styleable.LoadingLayout_emptyReTryId, 0)
        }

        if (resourceFailed == R.layout.loading_layout_failed) {
            retryIdFailed = R.id.ll_retry
        } else {
            retryIdFailed = ta.getResourceId(R.styleable.LoadingLayout_failedReTryId, 0)
        }

        if (resourceNoNet == R.layout.loading_layout_no_net) {
            retryIdNoNet = R.id.ll_retry
        } else {
            retryIdNoNet = ta.getResourceId(R.styleable.LoadingLayout_noNetReTryId, 0)
        }

        ta.recycle()
    }

    fun setOnRetryListener(onRetryListener: OnRetryListener) {
        this.onRetryListener = onRetryListener
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            retryIdEmpty -> onRetryListener?.onRetry(this)
            retryIdFailed -> onRetryListener?.onRetry(this)
            retryIdNoNet -> onRetryListener?.onRetry(this)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return state == LOADING || super.onInterceptTouchEvent(ev)
    }

    interface OnRetryListener {
        fun onRetry(view: LoadingLayout)
    }

    override fun onSaveInstanceState(): Parcelable {
        return SavedState(super.onSaveInstanceState(), state)
    }

    override fun onRestoreInstanceState(savedState: Parcelable?) {
        if (savedState !is SavedState) {
            super.onRestoreInstanceState(savedState)
            return
        }

        super.onRestoreInstanceState(savedState.superState)
        state = savedState.state
    }

    class SavedState : BaseSavedState {

        var state = SUCCESS

        constructor(source: Parcelable, state: Int) : super(source) {
            this.state = state
        }

        constructor(parcel: Parcel) : super(parcel) {
            state = parcel.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(state)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(parcel: Parcel): SavedState {
                return SavedState(parcel)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return super.generateLayoutParams(attrs)
    }
}
