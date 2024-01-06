package com.soethan.foodycompose.domain

import android.provider.ContactsContract.Data

sealed class DataState<out T:Any> {

    data class Success<out T:Any>(val data:T):DataState<T>()

    data class Error(val message:String?,val code:Int):DataState<Nothing>()

    data class Exception(val e:Throwable):DataState<Nothing>()

}




