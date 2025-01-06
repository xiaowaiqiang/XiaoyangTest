package com.szht.xiaoyangtest.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//使用object关键字让ServiceCreator成为了一个单例类
object ServiceCreator {
  private const val BASE_URL = "https://api.caiyunapp.com/"
  private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
//  1.val service = retrofit.create(PlaceService::class.java)
//  2.外部调用
//  val appService = ServiceCreator.create(AppService::class.java)
  fun <T> create(serviceClass:Class<T>):T = retrofit.create(serviceClass)
  //3.使用inline关键字来修饰方法， 使用reified关键字来修饰泛型，这是泛型实化的两大前提条件。
  //val appService = ServiceCreator.create<AppService>()
  inline fun <reified T> create():T = create(T::class.java)
}