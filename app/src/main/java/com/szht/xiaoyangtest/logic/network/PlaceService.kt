package com.szht.xiaoyangtest.logic.network

import com.szht.xiaoyangtest.MyApplication
import com.szht.xiaoyangtest.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
  @GET("v2/place?token=${MyApplication.TOKEN}&lang=zh_CN")
  fun searchPlaces(@Query("query") query:String): Call<PlaceResponse>
}