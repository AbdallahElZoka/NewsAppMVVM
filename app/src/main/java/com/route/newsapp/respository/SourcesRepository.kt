package com.route.newsapp.respository

import com.route.newsapp.api.model.SourcesItem

interface SourcesRepository {
    suspend fun getSources():List<SourcesItem?>?
    suspend fun cacheSources(data:List<SourcesItem?>?)
}