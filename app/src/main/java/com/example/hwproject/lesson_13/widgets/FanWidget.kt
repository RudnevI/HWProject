package com.example.hwproject.lesson_13.widgets

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.hwproject.R

class FanWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius = 0.0f
    private var fanSpeed = Speed.OFF
    private var pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = (kotlin.math.min(width, height) / 2.0 * 0.8).toFloat()
    }

    private fun PointF.computeXYForSpeed(pos: Speed, radius: Float) {
        val startAngle = Math.PI * (9 / 8.0)
        val angle = startAngle + pos.ordinal * (Math.PI / 4)
        x = (radius * kotlin.math.cos(angle)).toFloat() + width / 2
        y = (radius * kotlin.math.sin(angle)).toFloat() + height / 2

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = if (fanSpeed == Speed.OFF) Color.GRAY else Color.GREEN
        canvas?.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            radius,
            paint
        )
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSpeed(fanSpeed, markerRadius)
        paint.color = Color.BLACK
        canvas?.drawCircle(pointPosition.x, pointPosition.y, radius / 12, paint)
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in Speed.values()) {
            pointPosition.computeXYForSpeed(i, labelRadius)
            val label = resources.getString(i.label)
            canvas?.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }

    init {
        isClickable = true

    }

    override fun performClick(): Boolean {

        if (super.performClick()) return true

        fanSpeed = fanSpeed.next()
        contentDescription = resources.getString(fanSpeed.label)

        invalidate()
        return true
    }

    companion object {

        private const val RADIUS_OFFSET_LABEL = 30
        private const val RADIUS_OFFSET_INDICATOR = -35

        enum class Speed(val label: Int) {
            OFF(R.string.fan_off),
            LOW(R.string.fan_low),
            MEDIUM(R.string.fan_medium),
            HIGH(R.string.fan_high);

            fun next() = when (this) {
                OFF -> LOW
                LOW -> MEDIUM
                MEDIUM -> HIGH
                HIGH -> OFF

            }

        }
    }
}