package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.base

data class Record<out R>(
    val data: R?,
    val error: ErrorRecord?
)
