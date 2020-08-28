package com.example.consultacepkotlin


interface SimpleCallback<T> {
    fun onResponse(response: T?)
    fun onError(error: String?)
}