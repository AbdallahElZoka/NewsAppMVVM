package com.route.newsappc34.ui.home

import android.content.DialogInterface
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.newsappc34.Constants
import com.route.newsappc34.R
import com.route.newsappc34.api.ApiManager
import com.route.newsappc34.api.model.ArticlesItem
import com.route.newsappc34.api.model.NewsResponse
import com.route.newsappc34.api.model.SourcesItem
import com.route.newsappc34.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel :ViewModel() {

    // hold data
    // handle logic
    // Data Binding

    val sourcesLivesData = MutableLiveData<List<SourcesItem?>?>()
    val showProgressLiveData = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
    val newsLivedata  = MutableLiveData<List<ArticlesItem?>?>()
    fun getSources(){
        ApiManager.getApis()
            .getNewsSources(
                Constants.apiKey,
                "en",country = "us")
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                   // response.body().sources
                    showProgressLiveData.value = false

                    if(response.isSuccessful){// 200-299
                        sourcesLivesData.value = response.body()?.sources
                    }else {
                      messageLiveData.value = response.body()?.message
                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    showProgressLiveData.value = false
                    messageLiveData.value = t.localizedMessage
                }
            })
    }
    fun getNews(sourceId: String?) {
        showProgressLiveData.value=true
         newsLivedata.value = null
        ApiManager.getApis()
            .getNews(Constants.apiKey,"en",sourceId?:"","")
            .enqueue(object :Callback<NewsResponse>{
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                   showProgressLiveData.value = false

                    if(response.isSuccessful){// 200-299
                        newsLivedata.value = response.body()?.articles
                    }else {
                        messageLiveData.value = response.body()?.message
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    showProgressLiveData.value = false
                    messageLiveData.value = t.localizedMessage
                }
            })
    }


}