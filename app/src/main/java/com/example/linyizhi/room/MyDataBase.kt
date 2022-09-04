package com.example.linyizhi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [MessageBean::class], exportSchema = false)
abstract class MyDataBase: RoomDatabase() {
    abstract fun messageDao():MessageDao

    companion object{
        private var instance:MyDataBase ?=null
        @Synchronized
        fun getDataBase(context: Context):MyDataBase{
            instance?.let { return it }
            return Room.databaseBuilder(context.applicationContext,MyDataBase::class.java,"task_database")
                .fallbackToDestructiveMigration()
                .build().apply {
                    instance = this
                }
        }
    }
}