package com.route.newsappc34.respository.impl

import com.route.newsappc34.Constants
import com.route.newsappc34.api.WebServices
import com.route.newsappc34.api.model.SourcesItem
import com.route.newsappc34.dataBase.NewsDataBase
import com.route.newsappc34.respository.dataSource.SourcesOfflineDataSource
import com.route.newsappc34.respository.dataSource.SourcesOnlineDataSource
import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(val webServices: WebServices):
        SourcesOnlineDataSource{

    override suspend fun getSources(): List<SourcesItem?> {
        val sourcesResponse = webServices.getNewsSources(Constants.apiKey,"en","us");
        return sourcesResponse.sources!!
    }
}
class SourcesOfflineDataSourceImpl @Inject constructor(val dataBase:NewsDataBase)
    :SourcesOfflineDataSource{
    override suspend fun getSources(): List<SourcesItem> {
        val sources = dataBase.sourcesDao()
            .getNewsSources();
        return sources;
    }

    override suspend fun cacheSources(data: List<SourcesItem?>?) {
        dataBase.sourcesDao().cacheSources(data)
    }
}