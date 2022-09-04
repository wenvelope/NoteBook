package com.example.linyizhi.room

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class Repository(private val messageDao: MessageDao) {
    fun getMessage()= liveData(Dispatchers.IO){
        messageDao.selectAllMessages().collect{
            emit(it)
        }
    }

    fun insertMessage( vararg messageBean: MessageBean){
       messageBean.forEach {
           messageDao.insertMessage(it)
       }
    }

    fun deleteMessage(vararg messageBean: MessageBean){
        messageBean.forEach {
            messageDao.deleteMessage(it)
        }
    }

    fun updateMessage(vararg messageBean: MessageBean){
        messageBean.forEach {
            messageDao.updateMessage(it)
        }
    }

    fun selectMessage(string: String) = liveData(Dispatchers.IO){
        messageDao.selectSpecifiedMessages(string).collect{
            emit(it)
        }
    }
}