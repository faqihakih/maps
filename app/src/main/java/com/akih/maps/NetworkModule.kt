package com.akih.maps

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    private var retrofit: Retrofit? = null
    private val OKHTTP_TIMEOUT = 60L

    private val okHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(OKHTTP_TIMEOUT, TimeUnit.SECONDS)
    }.build()

    private fun instanceRetrofit() : Retrofit {
        if(retrofit == null){
            return Retrofit.Builder().apply {
                baseUrl(BuildConfig.API_MAPS_URL)
                addConverterFactory(GsonConverterFactory.create())
                client(okHttpClient)
            }.build()
        }
        return retrofit!!
    }
}