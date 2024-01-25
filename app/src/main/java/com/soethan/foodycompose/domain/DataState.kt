package com.soethan.foodycompose.domain

import android.provider.ContactsContract.Data

sealed class DataState<out T : Any> {

    data class Success<out T : Any>(val data: T) : DataState<T>()

    data class Error(val message: String?, val code: Int) : DataState<Nothing>()

    data class Exception(val e: Throwable) : DataState<Nothing>()

}


suspend fun <T : Any> DataState<T>.onSuccess(executable: suspend (T) -> Unit): DataState<T> =
    apply {
        if (this is DataState.Success<T>) {
            executable(data)
        }
    }

suspend fun <T : Any> DataState<T>.onError(executable: suspend (code: Int, message: String?) -> Unit): DataState<T> =
    apply {
        if (this is DataState.Error) {
            executable(code, message)
        }
    }

suspend fun <T : Any> DataState<T>.onException(executable: suspend (e: Throwable) -> Unit): DataState<T> =
    apply {
        if (this is DataState.Exception) {
            executable(e)
        }
    }


