package com.armmask.annoyingex

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class FetchAPI(context: Context) {

    companion object {
        val LINK = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
        val TAG = "armmask"
    }

    private lateinit var queue: RequestQueue;
    var annoyingList: List<String>

    init {
        annoyingList = emptyList()
    }

    fun fetchWithVolley(context: Context) {
        queue = Volley.newRequestQueue(context)
        var tempList = mutableListOf<String>()
        val request = JsonObjectRequest(
            Request.Method.GET, LINK, null,
            Response.Listener { response ->
                val jsonArray = response.getJSONArray("messages")
                val gson = Gson()
                tempList = gson.fromJson(jsonArray.toString(), List::class.java) as MutableList<String>
                Log.i(TAG, tempList.toString())
                annoyingList = tempList
            },
            Response.ErrorListener { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG)
            })
        queue.add(request)
    }
}