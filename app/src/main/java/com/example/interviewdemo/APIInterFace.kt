package com.example.interviewdemo

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.postalpincode.in/postoffice/"
interface APIInterFace {

@GET("Kanpur")
fun getPincode(): Call<List<PincodeResponse>>

    object newsService {
         val apiInstance: APIInterFace

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiInstance = retrofit.create(APIInterFace::class.java)
        }
    }
}