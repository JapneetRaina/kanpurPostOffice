package com.example.interviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var pincodeResponse  =  (mutableListOf <PostOfficeList>())
    private var adapter =  RecyclerViewAdapter(this@MainActivity, pincodeResponse)
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        getPinCode()
    }

    private fun getPinCode() {
        val pincodeApi = APIInterFace.newsService.apiInstance.getPincode()
        pincodeApi.enqueue(object : retrofit2.Callback<List<PincodeResponse>?> {
            override fun onResponse(
                call: Call<List<PincodeResponse>?>,
                response: Response<List<PincodeResponse>?>
            ) {
                pincodeResponse.clear()
                val pincode = response.body()
                if (response.isSuccessful) {
                    pincodeResponse.addAll(pincode!![0].PostOffice)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<PincodeResponse>?>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
    }
}