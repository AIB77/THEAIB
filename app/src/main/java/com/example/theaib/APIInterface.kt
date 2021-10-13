package com.example.theaib

import android.widget.EditText
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {

    @GET("test")
    fun getData(): Call<List<MyDataItem>>

    @Headers("Content-Type: application/json")
    @POST("/test/")
    fun addData(@Body userData:MyDataItem ):Call<MyDataItem>

    @Headers("Content-Type: application/json")
    @PUT("/test/{id}")
    fun updateusers(@Path("id") id: EditText):Call<MyDataItem>

    @Headers("Content-Type: application/json")
    @DELETE("/test/{id}")
    fun deleteusers(@Path("id") id: Int):Call<Void>
}