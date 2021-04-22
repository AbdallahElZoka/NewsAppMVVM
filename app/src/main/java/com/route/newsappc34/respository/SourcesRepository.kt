package com.route.newsappc34.respository

import com.route.newsappc34.api.model.SourcesItem

interface SourcesRepository {
    suspend fun getSources():List<SourcesItem?>?
    suspend fun cacheSources(data:List<SourcesItem?>?)
}