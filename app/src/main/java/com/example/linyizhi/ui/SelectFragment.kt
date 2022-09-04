package com.example.linyizhi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linyizhi.MainViewModel
import com.example.linyizhi.MyApplication
import com.example.linyizhi.R
import com.example.linyizhi.adapter.MessageAdapter
import com.example.linyizhi.databinding.FragmentSelectBinding


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
        mBinding.selectRecyclerView.adapter = MessageAdapter(mModel.messageSelectedList)

        mBinding.editSelect.addTextChangedListener {
            if(!it.isNullOrEmpty()){
             val content = it.toString()
             mModel.getSelectMessages(content)
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