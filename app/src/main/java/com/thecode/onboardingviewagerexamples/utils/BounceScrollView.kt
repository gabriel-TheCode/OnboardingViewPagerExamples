package com.code.variance.thedigitalbankuidemo.Utils

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.TranslateAnimation
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import com.thecode.onboardingviewagerexamples.R

class BounceScrollView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) :
    NestedScrollView(context, attrs, defStyleAttr) {
    var isScrollHorizontally: Boolean
    private var mDamping: Float
    var isIncrementalDamping: Boolean
    private var mBounceDelay: Long
    private var mTriggerOverScrollThreshold: Int
    private var mInterpolator: Interpolator? = null
    private var mChildView: View? = null
    private var mStart = 0f
    private var mPreDelta = 0
    private var mOverScrolledDistance = 0
    private val mNormalRect = Rect()
    private var mScrollListener: OnScrollListener? =
        null
    private var mOverScrollListener: OnOverScrollListener? =
        null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        0
    )

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (childCount > 0) {
            mChildView = getChildAt(0)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (childCount > 0) {
            val child = getChildAt(0)
            var childMeasuredHeight = child.measuredHeight
            if (childMeasuredHeight <= 0) return
            var childMeasuredWidth = child.measuredWidth
            val marginStart: Int
            val topMargin: Int
            val marginEnd: Int
            val bottomMargin: Int
            val lp = child.layoutParams
            if (lp is MarginLayoutParams) {
                marginStart = MarginLayoutParamsCompat.getMarginStart(lp)
                topMargin = lp.topMargin
                marginEnd = MarginLayoutParamsCompat.getMarginEnd(lp)
                bottomMargin = lp.bottomMargin
                if (marginStart != 0 || topMargin != 0 || marginEnd != 0 || bottomMargin != 0) {
                    if (childMeasuredHeight <= measuredHeight) {
                        childMeasuredWidth -= marginStart + marginEnd
                        childMeasuredHeight -= topMargin + bottomMargin
                    } else {
                        childMeasuredHeight += topMargin + bottomMargin
                    }
                    val widthSpec =
                        MeasureSpec.makeMeasureSpec(childMeasuredWidth, MeasureSpec.EXACTLY)
                    val heightSpec =
                        MeasureSpec.makeMeasureSpec(childMeasuredHeight, MeasureSpec.EXACTLY)
                    child.measure(widthSpec, heightSpec)
                }
            }
        }
    }

    override fun canScrollVertically(direction: Int): Boolean {
        return !isScrollHorizontally
    }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return isScrollHorizontally
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> mStart = if (isScrollHorizontally) ev.x else ev.y
            MotionEvent.ACTION_MOVE -> return if (isScrollHorizontally) {
                val scrollX = ev.x - mStart
                Math.abs(scrollX) >= mTriggerOverScrollThreshold
            } else {
                val scrollY = ev.y - mStart
                Math.abs(scrollY) >= mTriggerOverScrollThreshold
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (mChildView == null) return super.onTouchEvent(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                val now: Float
                val delta: Float
                val dampingDelta: Int
                now = if (isScrollHorizontally) ev.x else ev.y
                delta = mStart - now
                dampingDelta = (delta / calculateDamping()).toInt()
                mStart = now
                var onePointerTouch = true
                if (mPreDelta <= 0 && dampingDelta > 0) {
                    onePointerTouch = false
                } else if (mPreDelta >= 0 && dampingDelta < 0) {
                    onePointerTouch = false
                }
                mPreDelta = dampingDelta
                if (onePointerTouch && canMove(dampingDelta)) {
                    if (mNormalRect.isEmpty) {
                        mNormalRect[mChildView!!.left, mChildView!!.top, mChildView!!.right] =
                            mChildView!!.bottom
                    }
                    if (isScrollHorizontally) {
                        mChildView!!.layout(
                            mChildView!!.left - dampingDelta, mChildView!!.top,
                            mChildView!!.right - dampingDelta, mChildView!!.bottom
                        )
                    } else {
                        mChildView!!.layout(
                            mChildView!!.left, mChildView!!.top - dampingDelta,
                            mChildView!!.right, mChildView!!.bottom - dampingDelta
                        )
                    }
                    if (mOverScrollListener != null) {
                        mOverScrolledDistance += dampingDelta
                        mOverScrollListener!!.onOverScrolling(
                            mOverScrolledDistance <= 0,
                            Math.abs(mOverScrolledDistance)
                        )
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                performClick()
                if (!mNormalRect.isEmpty) {
                    resetChildViewWithAnimation()
                }
                mPreDelta = 0
                mOverScrolledDistance = 0
            }
            MotionEvent.ACTION_CANCEL -> {
                if (!mNormalRect.isEmpty) {
                    resetChildViewWithAnimation()
                }
                mPreDelta = 0
                mOverScrolledDistance = 0
            }
        }
        return super.onTouchEvent(ev)
    }

    private fun calculateDamping(): Float {
        var ratio: Float
        ratio = if (isScrollHorizontally) {
            Math.abs(mChildView!!.left) * 1.0f / mChildView!!.measuredWidth
        } else {
            Math.abs(mChildView!!.top) * 1.0f / mChildView!!.measuredHeight
        }
        ratio += 0.2f
        return if (isIncrementalDamping) {
            mDamping / (1.0f - Math.pow(ratio.toDouble(), 2.0).toFloat())
        } else {
            mDamping
        }
    }

    private fun resetChildViewWithAnimation() {
        val anim: TranslateAnimation
        val layoutParams = mChildView!!.layoutParams
        val fixedPadding: Int
        var fixedMargin = 0
        if (isScrollHorizontally) {
            if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                fixedPadding = ViewCompat.getPaddingEnd(this)
                if (layoutParams is MarginLayoutParams) {
                    fixedMargin =
                        MarginLayoutParamsCompat.getMarginEnd(layoutParams)
                }
            } else {
                fixedPadding = ViewCompat.getPaddingStart(this)
                if (layoutParams is MarginLayoutParams) {
                    fixedMargin =
                        MarginLayoutParamsCompat.getMarginStart(layoutParams)
                }
            }
            anim = TranslateAnimation(
                (mChildView!!.left - fixedPadding - fixedMargin).toFloat(),
                (mNormalRect.left - fixedPadding - fixedMargin).toFloat(),
                0F,
                0F
            )
        } else {
            fixedPadding = paddingTop
            if (layoutParams is MarginLayoutParams) {
                fixedMargin = layoutParams.topMargin
            }
            anim = TranslateAnimation(
                0F,
                0F,
                (mChildView!!.top - fixedPadding - fixedMargin).toFloat(),
                (mNormalRect.top - fixedPadding - fixedMargin).toFloat()
            )
        }
        anim.interpolator = mInterpolator
        anim.duration = mBounceDelay
        mChildView!!.startAnimation(anim)
        mChildView!!.layout(
            mNormalRect.left,
            mNormalRect.top,
            mNormalRect.right,
            mNormalRect.bottom
        )
        mNormalRect.setEmpty()
    }

    private fun canMove(delta: Int): Boolean {
        return if (delta != 0 && delta < 0) canMoveFromStart() else canMoveFromEnd()
    }

    private fun canMoveFromStart(): Boolean {
        return if (isScrollHorizontally) scrollX == 0 else scrollY == 0
    }

    private fun canMoveFromEnd(): Boolean {
        return if (isScrollHorizontally) {
            var offset = mChildView!!.measuredWidth - width
            offset = if (offset < 0) 0 else offset
            scrollX == offset
        } else {
            var offset = mChildView!!.measuredHeight - height
            offset = if (offset < 0) 0 else offset
            scrollY == offset
        }
    }

    override fun onScrollChanged(
        scrollX: Int,
        scrollY: Int,
        oldl: Int,
        oldt: Int
    ) {
        super.onScrollChanged(scrollX, scrollY, oldl, oldt)
        if (mScrollListener != null) {
            mScrollListener!!.onScrolling(scrollX, scrollY)
        }
    }

    var damping: Float
        get() = mDamping
        set(damping) {
            if (mDamping > 0) {
                mDamping = damping
            }
        }

    var bounceDelay: Long
        get() = mBounceDelay
        set(bounceDelay) {
            if (bounceDelay >= 0) {
                mBounceDelay = bounceDelay
            }
        }

    fun setBounceInterpolator(interpolator: Interpolator) {
        mInterpolator = interpolator
    }

    var triggerOverScrollThreshold: Int
        get() = mTriggerOverScrollThreshold
        set(threshold) {
            if (threshold >= 0) {
                mTriggerOverScrollThreshold = threshold
            }
        }

    fun setOnScrollListener(scrollListener: OnScrollListener?) {
        mScrollListener = scrollListener
    }

    fun setOnOverScrollListener(overScrollListener: OnOverScrollListener?) {
        mOverScrollListener = overScrollListener
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private class DefaultQuartOutInterpolator : Interpolator {
        override fun getInterpolation(input: Float): Float {
            return (1.0f - Math.pow(1 - input.toDouble(), 4.0)).toFloat()
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    interface OnScrollListener {
        fun onScrolling(scrollX: Int, scrollY: Int)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    interface OnOverScrollListener {
        /**
         * @param fromStart LTR, the left is start; RTL, the right is start.
         */
        fun onOverScrolling(fromStart: Boolean, overScrolledDistance: Int)
    }

    companion object {
        private const val DEFAULT_DAMPING_COEFFICIENT = 4.0f
        private const val DEFAULT_SCROLL_THRESHOLD = 20
        private const val DEFAULT_BOUNCE_DELAY: Long = 400
    }

    init {
        overScrollMode = View.OVER_SCROLL_NEVER
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false
        isFillViewport = true
        val a =
            context.obtainStyledAttributes(attrs, R.styleable.BounceScrollView, 0, 0)
        mDamping = a.getFloat(
            R.styleable.BounceScrollView_damping,
            DEFAULT_DAMPING_COEFFICIENT
        )
        val orientation = a.getInt(R.styleable.BounceScrollView_scrollOrientation, 0)
        isScrollHorizontally = orientation == 1
        isIncrementalDamping = a.getBoolean(R.styleable.BounceScrollView_incrementalDamping, true)
        mBounceDelay = a.getInt(
            R.styleable.BounceScrollView_bounceDelay,
            DEFAULT_BOUNCE_DELAY.toInt()
        ).toLong()
        mTriggerOverScrollThreshold = a.getInt(
            R.styleable.BounceScrollView_triggerOverScrollThreshold,
            DEFAULT_SCROLL_THRESHOLD
        )
        a.recycle()
        if (isIncrementalDamping) {
            mInterpolator =
                DefaultQuartOutInterpolator()
        }
    }
}