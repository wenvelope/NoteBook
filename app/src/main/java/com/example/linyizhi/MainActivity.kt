package com.example.linyizhi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.linyizhi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding:ActivityMainBinding
    companion object{
        lateinit var mInstance:MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        setContentView(R.layout.activity_main)
        mInstance = this
        val handler = Handler(mainLooper)
        handler.postDelayed({
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
        },2000)
    }
}