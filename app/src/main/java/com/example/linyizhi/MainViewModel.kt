package com.example.linyizhi

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.linyizhi.room.MessageBean
import com.example.linyizhi.room.MyDataBase
import com.example.linyizhi.room.Repository
import com.kongzue.dialogx.dialogs.PopTip

class MainViewModel: ViewModel() {
    private val dataBase by lazy { MyDataBase.getDataBase(MyApplication.context) }
    private val repository by lazy { Repository(dataBase.messageDao()) }


    val messageList = ArrayList<MessageBean>()

    private val _messageData = MutableLiveData<Any>()

    val messageData = Transformations.switchMap(_messageData){
        repository.getMessage()
    }

    fun getAllMessages(){
        _messageData.value = _messageData.value
    }

    fun insertMessage(vararg messageBean: MessageBean){
        repository.insertMessage(*messageBean)
    }
    fun deleteMessage(vararg messageBean: MessageBean){
        repository.deleteMessage(*messageBean)
    }
    fun updateMessage(vararg messageBean: MessageBean){
        repository.updateMessage(*messageBean)
    }


    val messageSelectedList = ArrayList<MessageBean>()

    private val _selectData = MutableLiveData<String>()

    val selectData = Transformations.switchMap(_selectData){
        repository.selectMessage(it)
    }

    fun getSelectMessages(string: String){
        _selectData.value = string
    }
}