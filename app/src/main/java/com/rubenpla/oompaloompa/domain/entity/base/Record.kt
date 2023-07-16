package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base

data class Record<out R>(
    val data: R?,
    val error: ErrorRecord?
)
