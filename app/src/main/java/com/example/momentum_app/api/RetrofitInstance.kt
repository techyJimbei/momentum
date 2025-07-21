package com.example.momentum_app.api

import android.content.Context
import com.example.momentum_app.jwttoken.AuthInterceptor
import com.example.momentum_app.jwttoken.TokenManager
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun provideOkHttpClient(tokenManager: TokenManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.10.207:8080/")
            .client(client) // Use client with logging
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }


}

