package com.szht.xiaoyangtest.logic

import androidx.lifecycle.liveData
import com.szht.xiaoyangtest.logic.model.Place
import com.szht.xiaoyangtest.logic.network.XyWeatherNetwork
import kotlinx.coroutines.Dispatchers

//仓库层的统一封装入口
object Repository {
//  为了能将异步获取的数据以响应式编程的方式通知给上一层，通常会返回一个LiveData对象
  fun searchPlaces(query:String)= liveData(Dispatchers.IO){
    val result = try {
      val response = XyWeatherNetwork.searchPlaces(query)
      if (response.status == "ok"){
        val places = response.places
        Result.success(places)
      }else{
        Result.failure(RuntimeException("response status is ${response.status}"))
      }
    }catch (e:Exception){
      Result.failure<List<Place>>(e)
    }
    emit(result)
  }
}