package com.szht.xiaoyangtest.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 统一的网络数据源访问入口
 */
object XyWeatherNetwork {
  private val placeService = ServiceCreator.create<PlaceService>()

  //定义了 一个searchPlaces()函数，并在这里调用刚刚在PlaceService接口中定义的 searchPlaces()方法，以发起搜索城市数据请求
  //suspend 挂起函数
  suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

  /*由于不同的Service接口返回的数据类型也不同，所以这次我们不能像刚才那样针对具体的类型进行编程了，而是要使用泛型的方式。定义一个await()函数*/
  //suspend 挂起函数 return suspendCoroutine 挂起 Continuation的resume()方法恢复被挂起的协程
  private suspend fun <T> Call<T>.await(): T {
    return suspendCoroutine { continuation ->
      enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
          val body = response.body()
          if (body != null) {
            continuation.resume(body)
          }else {
            continuation.resumeWithException(RuntimeException("response body is null"))
          }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
          continuation.resumeWithException(t)
        }

      })
    }
  }
}