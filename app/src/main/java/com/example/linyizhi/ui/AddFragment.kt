package com.example.linyizhi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.linyizhi.MainViewModel
import com.example.linyizhi.MyApplication
import com.example.linyizhi.R
import com.example.linyizhi.databinding.FragmentAddBinding
import com.example.linyizhi.room.MessageBean
import com.loper7.date_time_picker.DateTimeConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddFragment : Fragment() {
    private lateinit var mBinding:FragmentAddBinding
    private val mModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_add,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView(){
        mBinding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        mBinding.confirmButton.setOnClickListener {
            val title = mBinding.editTitle.text.toString()
            val taskMessage = mBinding.editTask.text.toString()
            val dateOfMonth = mBinding.timePicker.getPicker(DateTimeConfig.MONTH)?.value.toString()
            val dateOfDay = mBinding.timePicker.getPicker(DateTimeConfig.DAY)?.value.toString()
            lifecycleScope.launch(Dispatchers.IO){
                if(title.isEmpty()){
                    Toast.makeText(MyApplication.context,"标题不能为空",Toast.LENGTH_SHORT).show()
                }
                mModel.insertMessage(MessageBean(title,taskMessage,dateOfMonth+"月"+dateOfDay+"日",false))
            }
            mBinding.apply {
                editTask.setText("")
                editTitle.setText("")
            }
            Toast.makeText(MyApplication.context,"添加成功",Toast.LENGTH_SHORT).show()
        }

        mBinding.timePicker.apply {
            setDisplayType(
                intArrayOf(
                    DateTimeConfig.MONTH,
                    DateTimeConfig.DAY,
                )
            )
            setDefaultMillisecond(System.currentTimeMillis())
        }
    }





}