package com.example.interviewdemo

data class PincodeResponse(
    val Message: String,
    val Status: String,
    val PostOffice: List<PostOfficeList>
)
