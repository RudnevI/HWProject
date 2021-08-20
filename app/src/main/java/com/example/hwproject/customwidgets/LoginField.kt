package com.example.hwproject.customwidgets

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.EditText
import com.example.hwproject.R

class LoginField @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : androidx.appcompat.widget.AppCompatEditText(context, attrs, defStyleAttr){



    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if(this.text.isNullOrBlank())this.setBackgroundColor(resources.getColor(R.color.invalid_input_color))
    }
    // TODO: 20.08.2021 write working implementation 
}