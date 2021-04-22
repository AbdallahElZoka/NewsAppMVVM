package com.route.newsappc34.respository.impl

import com.route.newsappc34.NetworkAwareHandler
import com.route.newsappc34.api.model.SourcesItem
import com.route.newsappc34.respository.SourcesRepository
import com.route.newsappc34.respository.dataSource.SourcesOfflineDataSource
import com.route.newsappc34.respository.dataSource.SourcesOnlineDataSource

class SourcesRepositoryImpl(val onlineDataSource: SourcesOnlineDataSource,
        val offlineDataSource: SourcesOfflineDataSource,
        val networkAwareHandler: NetworkAwareHandler):SourcesRepository {
    override suspend fun cacheSources(data: List<SourcesItem?>?) {
        offlineDataSource.cacheSources(data)
    }

    override suspend fun getSources(): List<SourcesItem?>? {
        // if network available
        if(networkAwareHandler.isOnline()){
            // then get data from api
            val sources =  onlineDataSource.getSources();
            // then cache
            cacheSources(sources)
            return sources
        }
        return offlineDataSource.getSources()
    }
}