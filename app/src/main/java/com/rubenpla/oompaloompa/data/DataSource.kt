package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.data

import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.remote.NetworkManager

interface DataSource {
    fun api(): NetworkManager
}