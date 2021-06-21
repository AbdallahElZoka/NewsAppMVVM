package com.route.newsapp.respository

import com.route.newsapp.NetworkAwareHandler
import com.route.newsapp.NetworkAwareHandlerImpl
import com.route.newsapp.api.WebServices
import com.route.newsapp.dataBase.NewsDataBase
import com.route.newsapp.respository.dataSource.SourcesOfflineDataSource
import com.route.newsapp.respository.dataSource.SourcesOnlineDataSource
import com.route.newsapp.respository.impl.SourcesOfflineDataSourceImpl
import com.route.newsapp.respository.impl.SourcesOnlineDataSourceImpl
import com.route.newsapp.respository.impl.SourcesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule{

    @Provides
    fun provideSourcesOnlineDataSource(webServices: WebServices):SourcesOnlineDataSource{
        return SourcesOnlineDataSourceImpl(webServices);
    }
    @Provides
    fun provideOfflineDataSource(database:NewsDataBase):SourcesOfflineDataSource{
        return SourcesOfflineDataSourceImpl(database)
    }
    @Provides
    fun provideNetworkAwareHandler():NetworkAwareHandler{
        return NetworkAwareHandlerImpl.getInstance()
    }

    @Provides
    fun provideSourcesRespository(onlineDataSource: SourcesOnlineDataSource,
    offlineDataSource: SourcesOfflineDataSource,
    networkAwareHandler: NetworkAwareHandler):SourcesRepository{
        return SourcesRepositoryImpl(onlineDataSource,offlineDataSource,networkAwareHandler)
    }
}