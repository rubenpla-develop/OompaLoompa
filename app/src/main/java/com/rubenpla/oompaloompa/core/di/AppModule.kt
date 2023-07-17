package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.core.di

import android.content.Context
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.data.DataSource
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.data.DataSourceImpl
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.rest.RestApi
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.rest.RestApiImpl
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.retrofit.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesContext(@ApplicationContext context: Context) = context

    @Provides
    fun providesRestApi(retrofitApi: RetrofitApi): RestApi = RestApiImpl(retrofitApi = retrofitApi)

    @Provides
    fun providesDataSource(restApi: RestApi): DataSource = DataSourceImpl(restApi = restApi)

    @Provides
    fun providesDispatcher() = Dispatchers.IO
}