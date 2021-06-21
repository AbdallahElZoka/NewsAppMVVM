package com.route.newsapp.respository.dataSource

import com.route.newsapp.api.model.SourcesItem

interface SourcesOnlineDataSource{
    suspend fun getSources():List<SourcesItem?>
}
interface SourcesOfflineDataSource{
    suspend fun getSources():List<SourcesItem?>
    suspend fun cacheSources(data:List<SourcesItem?>?)
}