package com.example.linyizhi.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {
    @Insert
    fun insertMessage(messageBean: MessageBean)

    @Query("select * from messages where isFinished = 0")
    fun selectAllMessages():Flow<List<MessageBean>>

    @Delete
    fun deleteMessage(messageBean: MessageBean)

    @Update
    fun updateMessage(messageBean: MessageBean)

    @Query("select * from messages where message like '%'||(:string)||'%' and isFinished = 0 or title like '%'||(:string)||'%' ")
    fun selectSpecifiedMessages(string: String):Flow<List<MessageBean>>
}