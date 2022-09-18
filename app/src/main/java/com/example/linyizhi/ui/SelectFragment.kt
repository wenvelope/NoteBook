package com.example.linyizhi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linyizhi.MainViewModel
import com.example.linyizhi.MyApplication
import com.example.linyizhi.R
import com.example.linyizhi.adapter.MessageAdapter
import com.example.linyizhi.databinding.FragmentSelectBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class SelectFragment : Fragment() {
    private lateinit var mBinding:FragmentSelectBinding
    private val mModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_select,container,false)
        return mBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.selectRecyclerView.layoutManager = LinearLayoutManager(MyApplication.context,LinearLayoutManager.VERTICAL,false)
        mBinding.selectRecyclerView.addItemDecoration(DividerItemDecoration(MyApplication.context,DividerItemDecoration.VERTICAL))
        mBinding.selectRecyclerView.adapter = MessageAdapter(mModel.messageSelectedList).apply {
            setOnItemClickListener(object:MessageAdapter.OnItemClickListener{
                var flag = false
                override fun onClick(view: View, position: Int) {
                    val contentView = view.findViewById<TextView>(R.id.item_message_content)
                    contentView.text = mModel.messageSelectedList[position].message
                    if(!flag){
                        contentView.visibility = View.VISIBLE
                        flag =!flag
                    }else{
                        contentView.visibility = View.GONE
                        flag=!flag
                    }
                }
            })
            setOnButtonClickListener(object :MessageAdapter.OnItemClickListener{
                override fun onClick(view: View, position: Int) {
                    view.isEnabled=false
                    val handler = Handler(Looper.getMainLooper())
                    val message = mModel.messageSelectedList[position]
                    message.isFinished = true
                    handler.postDelayed({
                        lifecycleScope.launch(Dispatchers.IO){
                            mModel.updateMessage(message)
                        }
                    },1000)
                }
            })
        }

        mBinding.editSelect.addTextChangedListener {
            if(!it.isNullOrEmpty()){
                mBinding.selectRecyclerView.visibility = View.VISIBLE
             val content = it.toString()
             mModel.getSelectMessages(content)
            }else{
                mBinding.selectRecyclerView.visibility = View.INVISIBLE
            }
        }
        activity?.apply {
            mModel.selectData.observe(this){
                mModel.messageSelectedList.clear()
                mModel.messageSelectedList.addAll(it)
                mBinding.selectRecyclerView.adapter?.notifyDataSetChanged()
            }
        }

    }

}