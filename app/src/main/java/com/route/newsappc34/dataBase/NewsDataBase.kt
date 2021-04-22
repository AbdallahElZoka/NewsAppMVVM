package com.route.newsappc34.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newsappc34.api.model.SourcesItem
import com.route.newsappc34.dataBase.dao.SourcesDao

@Database(entities = [SourcesItem::class],version = 1,exportSchema = false)
abstract class NewsDataBase :RoomDatabase(){
    abstract fun sourcesDao():SourcesDao
    companion object{
        var newsDataBase:NewsDataBase?=null
        fun init(context: Context){
            newsDataBase = Room.databaseBuilder(context,NewsDataBase::class.java,"NEWS_DB")
                .fallbackToDestructiveMigration()
                .build()

        }

        fun getInstance():NewsDataBase{
         return newsDataBase!!
        }
    }
}