package com.arpitbandil.multifunctionalbutton.listeners

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.HapticFeedbackConstants
import android.view.MotionEvent
import android.view.View

class MultiFunctionalTouchListener(val view: View, val eventListener: EventListener) :
    View.OnTouchListener {

    val handler = Looper.myLooper()?.let { Handler(it) }

    private var downTime = 0L
    private var clickCount = 0

    private val holdRunnable = object : Runnable {
        override fun run() {
            if (view.isEnabled) {
                eventListener.onHolding(view)
                handler?.postDelayed(this, 150)
            }
        }
    }

    private val holdStart = Runnable { eventListener.onHoldStart(view) }

    private val clickAction = Runnable {
        clickCount = 0
        eventListener.onSingleClick(view)
    }

    init {
        view.setOnClickListener(null)
        view.setOnLongClickListener(null)
    }

    override fun onTouch(v: View?, e: MotionEvent?): Boolean {
        if (v?.id == view.id)
            when (e?.action) {
                MotionEvent.ACTION_DOWN -> {
                    downTime = System.currentTimeMillis()
                    handler?.postDelayed(holdStart, 200)
                    handler?.postDelayed(holdRunnable, 200)
                    handler?.removeCallbacks(clickAction)
                    v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                    return true
                }

                MotionEvent.ACTION_UP -> {
                    if ((System.currentTimeMillis() - downTime) < 200) { // Click Performed
                        when (clickCount) {
                            0 -> {
                                handler?.postDelayed(clickAction, 200)
                                clickCount += 1
                                v.performClick()
                            }

                            else -> {
                                eventListener.onDoubleClick(v)
                                clickCount = 0
                            }
                        }
                    } else {
                        clickCount = 0
                        eventListener.onHoldEnd(v)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY_RELEASE)
                        }
                    }
                    handler?.removeCallbacks(holdStart)
                    handler?.removeCallbacks(holdRunnable)
                    return true
                }
            }
        return v?.id == view.id
    }

    interface EventListener {
        fun onSingleClick(v: View?) {}
        fun onHoldStart(v: View?) {}
        fun onHolding(v: View?) {}
        fun onHoldEnd(v: View?) {}
        fun onDoubleClick(v: View?) {}
    }

}

fun View.setMultiFunctionalTouchListener(eventListener: MultiFunctionalTouchListener.EventListener) {
    setOnTouchListener(MultiFunctionalTouchListener(this, eventListener))
}
