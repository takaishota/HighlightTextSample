package com.example.android.st0124.highlighttextsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTextView()
        setupRealm()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun setupRealm() {
        Realm.init(this)
        realm = Realm.getDefaultInstance()
    }

    fun setupTextView() {
        textView.setTextIsSelectable(true)
//        textView.highlightColor = Color.parseColor("red")
//        ColorTextViewHandles.colorHandles(textView, Color.parseColor("red"))
    }
}
