package com.example.buidlingforecast.data.network

import okhttp3.logging.HttpLoggingInterceptor

class LoggingInterceptorImpl :LoggingInterceptor {

    override fun addingLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

}
