package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.rest.RestApi

interface NetworkManager {
    fun restApi(): RestApi
}