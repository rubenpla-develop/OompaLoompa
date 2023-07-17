package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.data

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.NetworkManager
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.NetworkManagerImpl
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.rest.RestApi
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val restApi: RestApi): DataSource {
    override fun api(): NetworkManager = NetworkManagerImpl(restApi = restApi)
}