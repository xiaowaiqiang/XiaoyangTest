package com.szht.xiaoyangtest

import android.app.Application
import android.content.Context

class MyApplication:Application() {
  companion object{
    lateinit var context:Context
    const val TOKEN = "oP5fkW9HzLgke48f"
  }

  override fun onCreate() {
    super.onCreate()
    context = applicationContext
  }
}