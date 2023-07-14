package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.core.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.data.WorkerListClient
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideGson() : Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun providesCache(context: Context): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() //10 MB
        val httpCacheDirectory = File(context.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }

    @Provides
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
/*        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addNetworkInterceptor(loggingInterceptor)
        }*/
        okHttpClient.cache(cache)
        okHttpClient.writeTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.readTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(ApiConstants.TIMEOUT, TimeUnit.SECONDS)
        return okHttpClient.build()
    }


    @Singleton
    @Provides
    fun providesRetrofitApi(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideWorkerListClient(retrofit: Retrofit) : WorkerListClient {
        return retrofit.create(WorkerListClient::class.java)
    }


}