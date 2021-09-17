package com.example.hwproject.lesson_13.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import com.example.hwproject.R
import com.example.hwproject.databinding.WidgetFeedbackBinding

class FeedbackWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: WidgetFeedbackBinding =
        WidgetFeedbackBinding.inflate(
            LayoutInflater.from(context)
        )

    init {
        addView(binding.root)

        context.withStyledAttributes(
            attrs,
            R.styleable.FeedbackWidget
        ) {
            binding.apply {
                feedbackTitle.hint = getString(R.styleable.FeedbackWidget_titleHint)
                feedbackButton.text = getString(R.styleable.FeedbackWidget_buttonText)
                feedbackButton.setTextColor(getColor(R.styleable.FeedbackWidget_buttonColor, Color.BLACK))
                feedbackButton.textSize = getDimension(R.styleable.FeedbackWidget_buttonTextSize,
                16f)
            }
        }
    }

    @SuppressWarnings("Callback Memory Leak")
    fun onEndIconClick(callback: (String) -> Unit) {
        binding.feedbackTitleEndIcon.setOnClickListener {
            callback.invoke(binding.feedbackTitle.text.toString())
        }
    }
}