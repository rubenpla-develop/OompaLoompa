package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.entity.base

sealed class ErrorRecord {
    object ClientError: ErrorRecord()
    object ServerError: ErrorRecord()
    object NetworkError: ErrorRecord()
    object GenericError: ErrorRecord()
}
