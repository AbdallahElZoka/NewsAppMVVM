package com.route.newsappc34.respository.dataSource

import com.route.newsappc34.api.model.SourcesItem

interface SourcesOnlineDataSource{
    suspend fun getSources():List<SourcesItem?>
}
interface SourcesOfflineDataSource{
    suspend fun getSources():List<SourcesItem?>
    suspend fun cacheSources(data:List<SourcesItem?>?)
}