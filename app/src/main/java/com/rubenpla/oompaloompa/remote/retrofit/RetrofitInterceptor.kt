package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.retrofit

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.ApiConstants
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.RemoteException
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RetrofitInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val requestBuilder = chain.request().newBuilder()
            val url = chain.request().url

            requestBuilder.url(url)
            val response = chain.proceed(requestBuilder.build())
            when (response.code) {
                in HttpURLConnection.HTTP_BAD_REQUEST..ApiConstants.STATUS_CODE_499 -> {
                    throw RemoteException.ClientError(response.message)
                }
                in HttpURLConnection.HTTP_INTERNAL_ERROR..ApiConstants.STATUS_CODE_599 -> {
                    throw RemoteException.ServerError(response.message)
                }
                in HttpURLConnection.HTTP_FORBIDDEN..ApiConstants.STATUS_CODE_403 -> {
                    throw RemoteException.ServerError(response.message)
                }
                else -> return response
            }
        } catch (e: Exception) {
            throw  when (e) {
                is UnknownHostException -> RemoteException.NoNetworkError(e.message.toString())
                is SocketTimeoutException -> RemoteException.NoNetworkError(e.message.toString())
                is RemoteException -> e
                else -> RemoteException.GenericError(e.message.toString())
            }
        }
    }
}