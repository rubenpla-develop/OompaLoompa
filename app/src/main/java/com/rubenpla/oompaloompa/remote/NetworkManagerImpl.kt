package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.rest.RestApi
import javax.inject.Inject

class NetworkManagerImpl @Inject constructor(private val restApi: RestApi): NetworkManager {

    override fun restApi(): RestApi = restApi
}