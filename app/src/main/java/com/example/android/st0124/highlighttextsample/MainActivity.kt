package com.example.android.st0124.highlighttextsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.st0124.highlighttextsample.util.HighlightRepository
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRealm()
        setupTextView()
        setupButton()
        setupHighlights()
    }

    override fun onStart() {
        super.onStart()
        setupHighlights()
    }


    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    private fun setupRealm() {
        Realm.init(this)
        realm = Realm.getDefaultInstance()
    }

    private fun setupTextView() {
        textView.setTextIsSelectable(true)
//        textView.highlightColor = Color.parseColor("red")
//        ColorTextViewHandles.colorHandles(textView, Color.parseColor("red"))
    }

    private fun setupHighlights() {
        val results = HighlightRepository().getAll(Highlight::class)
        println("results $results")
        results.forEach {
            textView.setHighlight(it.startIndex, it.endIndex)
        }
    }

    fun setupButton() {
        clearButton.setOnClickListener {
            textView.clearHighlight()
        }
    }


}
