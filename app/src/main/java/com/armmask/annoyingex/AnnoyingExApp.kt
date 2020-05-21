package com.armmask.annoyingex

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class AnnoyingExApp: Application() {

    companion object {
        const val NAME = "The Annoying Ex"
    }

    lateinit var annoyingExManager: AnnoyingExManager
    lateinit var fetchAPI: FetchAPI

    override fun onCreate() {
        super.onCreate()
        annoyingExManager = AnnoyingExManager(this)
        fetchAPI = FetchAPI(this)

    }



}