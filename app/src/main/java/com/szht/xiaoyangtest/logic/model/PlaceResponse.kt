package com.szht.xiaoyangtest.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse (val status:String,val places:List<Place> )

/*于JSON中一些字段的命名可能与Kotlin的命名规范不太
一致，因此这里使用了@SerializedName注解的方式，来让JSON字段和Kotlin字段之间建立
映射关系。*/
data class Place (val name:String,val location:Location,@SerializedName("")val address:String)

data class Location (val lat:String,val lng:String)