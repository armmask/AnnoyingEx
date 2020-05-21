package com.armmask.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.armmask.annoyingex.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var annoyingExManager: AnnoyingExManager
    lateinit var fetchAPI: FetchAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val display = intent.getStringExtra("message")

        display?.let {
            annoyingText.text = it
        }

        annoyingExManager = (application as AnnoyingExApp).annoyingExManager
        fetchAPI = (application as AnnoyingExApp).fetchAPI

        startAnnoy.setOnClickListener() {
            annoyingExManager.startAnnoyingEx()
        }

        endAnnoy.setOnClickListener {
            annoyingExManager.stopAnnoyingEx()
        }

    }
}
