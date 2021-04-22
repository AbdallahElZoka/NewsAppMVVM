package com.route.newsappc34.respository

import com.route.newsappc34.NetworkAwareHandler
import com.route.newsappc34.NetworkAwareHandlerImpl
import com.route.newsappc34.api.WebServices
import com.route.newsappc34.dataBase.NewsDataBase
import com.route.newsappc34.respository.dataSource.SourcesOfflineDataSource
import com.route.newsappc34.respository.dataSource.SourcesOnlineDataSource
import com.route.newsappc34.respository.impl.SourcesOfflineDataSourceImpl
import com.route.newsappc34.respository.impl.SourcesOnlineDataSourceImpl
import com.route.newsappc34.respository.impl.SourcesRepositoryImpl
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