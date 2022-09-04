package com.example.linyizhi

import android.annotation.SuppressLint
import android.app.Application
import android.app.Dialog
import android.content.Context
import com.kongzue.dialogx.DialogX

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DialogX.init(this)
        context = applicationContext
    }

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context:Context
    }
}