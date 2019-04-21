package com.example.buidlingforecast.data.network

import okhttp3.logging.HttpLoggingInterceptor

interface LoggingInterceptor {
    fun addingLoggingInterceptor() : HttpLoggingInterceptor
}