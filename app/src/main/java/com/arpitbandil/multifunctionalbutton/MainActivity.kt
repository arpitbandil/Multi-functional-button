package com.arpitbandil.multifunctionalbutton

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.arpitbandil.multifunctionalbutton.listeners.MultiFunctionalTouchListener
import com.arpitbandil.multifunctionalbutton.listeners.setMultiFunctionalTouchListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayButtonDemo()
    }

    @SuppressLint("SetTextI18n")
    private fun displayButtonDemo() {
        var count = 0
        val textview = findViewById<TextView>(R.id.tv)
        findViewById<Button>(R.id.btn).setMultiFunctionalTouchListener(object :
            MultiFunctionalTouchListener.EventListener {
            override fun onDoubleClick(v: View?) {
                textview.text = "Double Click Detected"
            }

            override fun onHoldEnd(v: View?) {
                Toast.makeText(this@MainActivity, "Hold Ended", Toast.LENGTH_SHORT).show()
                count = 0
            }

            override fun onHoldStart(v: View?) {
                Toast.makeText(this@MainActivity, "Hold Started", Toast.LENGTH_SHORT).show()
            }

            override fun onHolding(v: View?) {
                textview.text = "Count: $count\n Time(MS):${System.currentTimeMillis()}"
                count += 1
            }

            override fun onSingleClick(v: View?) {
                textview.text = "Single Click Detected"

            }
        })

    }

}