package com.example.linyizhi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.linyizhi.databinding.ActivityMain2Binding
import com.kongzue.dialogx.dialogs.FullScreenDialog
import com.kongzue.dialogx.interfaces.OnBindView

class MainActivity2 : AppCompatActivity() {
    private lateinit var mBinding: ActivityMain2Binding
    private lateinit var mModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView<ActivityMain2Binding>(this,R.layout.activity_main2)
        mModel = ViewModelProvider(this)[MainViewModel::class.java]
        //关闭广告页
        MainActivity.mInstance.finish()

        val naviHostFragment =supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val naviController = naviHostFragment.findNavController()

        mBinding.bottomNavi.setupWithNavController(naviController)


    }
}