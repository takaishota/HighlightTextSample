package com.example.android.st0124.highlighttextsample

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.util.AttributeSet
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.android.st0124.highlighttextsample.util.HighlightRepository
import java.util.*


class HighlightTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextView(context, attrs, defStyleAttr) {
    var selStartIndex = 0
    var selEndIndex = 0

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setupTextSelection()
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        println("getSelectedText ${text.subSequence(selStart, selEnd)}")
        println("selStart ${selStart}")
        println("selEnd ${selEnd}")

        selStartIndex = selStart
        selEndIndex = selEnd
    }

    fun setHighlight(startIndex: Int, endIndex: Int, selectedText: String = "") {
        val sb = SpannableStringBuilder(text)
        sb.setSpan(BackgroundColorSpan(Color.YELLOW), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text = sb
        clearFocus()
    }

    fun clearHighlight() {
        HighlightRepository().deleteAll()

        val ss = text as SpannableString
        val spans = ss.getSpans(0, text.length, BackgroundColorSpan::class.java)
        spans.forEach {
            ss.removeSpan(it)
        }
    }

    fun shareAction(text: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, text)
        sendIntent.type = "text/plain"
        context.startActivity(sendIntent)
        clearFocus()
    }

    fun copyAction(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("selected", text)
        clipboard.primaryClip = clip
        clearFocus()
    }

    private fun setupTextSelection() {
        this.customSelectionActionModeCallback = object : ActionMode.Callback2() {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                val inflater = mode?.menuInflater
                inflater?.inflate(R.menu.text_selection, menu)
                menu?.removeItem(android.R.id.selectAll)
                menu?.removeItem(android.R.id.copy)
                menu?.removeItem(android.R.id.shareText)
                menu?.removeItem(android.R.id.textAssist)

                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                val selected = text.substring(selStartIndex, selEndIndex)

                when (item?.itemId) {

                    R.id.copy -> {
                        copyAction(selected)
                        return true
                    }

                    R.id.share -> {
                        shareAction(selected)
                        return true
                    }

                    R.id.highlight -> {
                        storeHighlight()
                        setHighlight(selStartIndex, selEndIndex)
                        return true
                    }
                }
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                println("onDestroyActionMode")
            }
        }
    }

    private fun storeHighlight() {
        val highlight = Highlight(UUID.randomUUID().toString(), text.substring(selStartIndex, selEndIndex), selStartIndex, selEndIndex, Date())
        HighlightRepository().store(highlight)
    }
}