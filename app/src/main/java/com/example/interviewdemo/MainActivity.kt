package com.example.interviewdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private var pincodeResponse  =  (mutableListOf <PostOfficeList>())
    private var adapter =  RecyclerViewAdapter(this@MainActivity, pincodeResponse)
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        progressBar.visibility = View.VISIBLE

        getPinCode()
    }

    private fun getPinCode() {
        val pincodeApi = APIInterFace.newsService.apiInstance.getPincode()
        pincodeApi.enqueue(object : retrofit2.Callback<List<PincodeResponse>?> {
            override fun onResponse(
                call: Call<List<PincodeResponse>?>,
                response: Response<List<PincodeResponse>?>
            ) {
                progressBar.visibility = View.GONE
                pincodeResponse.clear()
                val pincode = response.body()
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, pincode!![0].Status, Toast.LENGTH_SHORT).show()
                    pincodeResponse.addAll(pincode!![0].PostOffice)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<PincodeResponse>?>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
    }
}