package com.example.android.st0124.highlighttextsample

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.util.AttributeSet
import android.widget.TextView

class HighlightTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextView(context, attrs, defStyleAttr) {
    var selStartIndex = 0
    var selEndIndex = 0

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        println("getSelectedText ${text.subSequence(selStart, selEnd)}")
        println("selStart ${selStart}")
        println("selEnd ${selEnd}")

        selStartIndex = selStart
        selEndIndex = selEnd
    }

    fun setHighlight() {
        val sb = SpannableStringBuilder(text)
        sb.setSpan(BackgroundColorSpan(Color.YELLOW), selStartIndex, selEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = sb
        clearFocus()
    }
}