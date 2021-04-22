package com.route.newsappc34.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.route.newsappc34.api.model.SourcesItem

@Dao
interface SourcesDao {
    @Query("select * from sourcesitem")
    suspend fun getNewsSources():List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheSources(data:List<SourcesItem?>?)

}