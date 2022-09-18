package com.example.linyizhi.adapter

import android.media.audiofx.DynamicsProcessing
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.linyizhi.R
import com.example.linyizhi.databinding.TasklistItemBinding
import com.example.linyizhi.room.MessageBean

class MessageAdapter(private val list: List<MessageBean>):RecyclerView.Adapter<MessageAdapter.MessageHolder>() {

    interface OnItemClickListener{
        fun onClick(view: View,position:Int)
    }

    private  var mOnItemClickListener: OnItemClickListener ?=null

    private var mOnButtonClickListener:OnItemClickListener ?=null

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener
    }
    fun setOnButtonClickListener(mOnButtonClickListener: OnItemClickListener){
        this.mOnButtonClickListener = mOnButtonClickListener
    }


    inner class MessageHolder(private val mBinding:TasklistItemBinding):RecyclerView.ViewHolder(mBinding.root){
        fun bind(position: Int){
            mBinding.title.text = list[position].title
            mBinding.date.text = list[position].dateTime
            mBinding.finishButton.isEnabled = !list[position].isFinished
            mBinding.title.setOnClickListener {
                mOnItemClickListener?.onClick(mBinding.root,position)
            }
            mBinding.finishButton.setOnClickListener {
                mOnButtonClickListener?.onClick(mBinding.finishButton,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val mBinding = DataBindingUtil.inflate<TasklistItemBinding>(LayoutInflater.from(parent.context), R.layout.tasklist_item,parent,false)
        return MessageHolder(mBinding)
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}