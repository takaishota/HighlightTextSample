package com.example.android.st0124.highlighttextsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTextView()
    }

    fun setupTextView() {
        textView.setTextIsSelectable(true)
//        textView.highlightColor = Color.parseColor("red")
//        ColorTextViewHandles.colorHandles(textView, Color.parseColor("red"))
    }
}
