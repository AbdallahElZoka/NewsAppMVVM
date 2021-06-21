package com.route.newsapp.respository.impl

import com.route.newsapp.Constants
import com.route.newsapp.api.WebServices
import com.route.newsapp.api.model.SourcesItem
import com.route.newsapp.dataBase.NewsDataBase
import com.route.newsapp.respository.dataSource.SourcesOfflineDataSource
import com.route.newsapp.respository.dataSource.SourcesOnlineDataSource
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