package com.route.newsappc34.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsappc34.Constants
import com.route.newsappc34.NetworkAwareHandlerImpl
import com.route.newsappc34.api.WebServices
import com.route.newsappc34.api.model.ArticlesItem
import com.route.newsappc34.api.model.NewsResponse
import com.route.newsappc34.api.model.SourcesItem
import com.route.newsappc34.dataBase.NewsDataBase
import com.route.newsappc34.respository.SourcesRepository
import com.route.newsappc34.respository.dataSource.SourcesOfflineDataSource
import com.route.newsappc34.respository.dataSource.SourcesOnlineDataSource
import com.route.newsappc34.respository.impl.SourcesOfflineDataSourceImpl
import com.route.newsappc34.respository.impl.SourcesOnlineDataSourceImpl
import com.route.newsappc34.respository.impl.SourcesRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val sourcesRepository: SourcesRepository,
val webServices:WebServices) :ViewModel() {

    // hold data
    // handle logic
    // Data Binding

    val sourcesLivesData = MutableLiveData<List<SourcesItem?>?>()
    val showProgressLiveData = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
    val newsLivedata  = MutableLiveData<List<ArticlesItem?>?>()
    fun getSources(){
        showProgressLiveData.value = true
       viewModelScope.launch {
           try {
               val sources =  sourcesRepository
                   .getSources();
               // response.body().sources
                sourcesLivesData.value = sources
               showProgressLiveData.value = false

           }catch (exception:Exception){
               showProgressLiveData.value = false
               messageLiveData.value = exception.localizedMessage

           }
       }
    }

    val authorLiveData = MutableLiveData<String>()

    fun getNews(sourceId: String?) {
        showProgressLiveData.value=true
         newsLivedata.value = null
        webServices
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