package com.example.linyizhi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.linyizhi.MainViewModel
import com.example.linyizhi.MyApplication
import com.example.linyizhi.R
import com.example.linyizhi.adapter.MessageAdapter
import com.example.linyizhi.databinding.FragmentMainBinding
import com.kongzue.dialogx.dialogs.BottomDialog
import com.kongzue.dialogx.dialogs.FullScreenDialog
import com.kongzue.dialogx.interfaces.OnBindView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.POST


class MainFragment : Fragment() {
    private lateinit var mBinding:FragmentMainBinding
    private  val mModel:MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView(){
        mBinding.taskListView.layoutManager = LinearLayoutManager(MyApplication.context,LinearLayoutManager.VERTICAL,false)
        mBinding.taskListView.addItemDecoration(DividerItemDecoration(MyApplication.context,DividerItemDecoration.VERTICAL))
        mBinding.taskListView.adapter = MessageAdapter(mModel.messageList).apply {
            setOnItemClickListener(object :MessageAdapter.OnItemClickListener{
                var flag = false
                override fun onClick(view: View, position: Int) {
                    val contentView = view.findViewById<TextView>(R.id.item_message_content)
                    contentView.text = mModel.messageList[position].message
                    if(!flag){
                        contentView.visibility = View.VISIBLE
                        flag=!flag
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
                    val message = mModel.messageList[position]
                    message.isFinished = true
                    handler.postDelayed({
                        lifecycleScope.launch(Dispatchers.IO){
                            mModel.updateMessage(message)
                        }
                    },1000)
                }
            })
        }

        activity?.apply {
            mModel.messageData.observe(this){
                mModel.messageList.clear()
                mModel.messageList.addAll(it)
                mModel.messageList.sortBy { it -> it.dateTime}
                mBinding.taskListView.adapter?.notifyDataSetChanged()
            }
        }

        mModel.getAllMessages()
    }



}