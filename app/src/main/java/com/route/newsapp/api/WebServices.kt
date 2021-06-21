package com.route.newsapp.api

import com.route.newsapp.api.model.NewsResponse
import com.route.newsapp.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("sources")
    suspend fun getNewsSources(@Query("apiKey") key:String,
                       @Query("language")lang:String,
                       @Query("country")country:String):SourcesResponse
    // concurrency
    @GET("everything")
    fun getNews(@Query("apiKey") key:String,
                       @Query("language")lang:String,
                       @Query("sources")sources:String,
                       @Query("q")searchKeyword:String):Call<NewsResponse>

}