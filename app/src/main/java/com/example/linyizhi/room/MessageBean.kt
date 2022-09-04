package com.example.linyizhi.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageBean(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "message")
    val message: String,
    @ColumnInfo(name = "dateTime")
    val dateTime:String,
    @ColumnInfo(name = "isFinished")
    var isFinished:Boolean
){
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}
