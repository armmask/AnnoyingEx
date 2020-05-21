package com.armmask.annoyingex

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    
    companion object {
        val LINK = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
        val TAG = "armmask"
    }
    
    private lateinit var list: List<String>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchWithVolley()

        

    }

    private fun fetchWithVolley() {
        val queue = Volley.newRequestQueue(this)
        var tempList = mutableListOf<String>()
        val request = JsonObjectRequest(Request.Method.GET, LINK, null,
            Response.Listener { response ->
                val jsonArray = response.getJSONArray("messages")
                repeat(jsonArray.length()) { index ->
                    val str = jsonArray.getString(index);
                    str?.let {
                        tempList.add(it);
                    }
                }
                Log.i(TAG, tempList.toString())
                list = tempList
            },
            Response.ErrorListener {error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG)
            })
        queue.add(request)
    }
}
