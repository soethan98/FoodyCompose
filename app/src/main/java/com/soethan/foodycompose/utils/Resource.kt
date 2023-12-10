package com.soethan.foodycompose.utils

sealed class Resource<out T : Any> {



    object Idle: Resource<Nothing>()
    object Loading : Resource<Nothing>()
    data class Content<out T : Any>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Content -> "Success with data:$data"
            is Error -> "Error : $message"
            Loading -> "Loading"
            Idle -> "Idle"
        }
    }
}