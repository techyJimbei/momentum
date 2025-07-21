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

    private lateinit var retrofit: Retrofit
    private lateinit var userApi: UserApi

    fun init(context: Context) {
        val tokenManager = TokenManager(context)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.10.207:8080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        userApi = retrofit.create(UserApi::class.java)
    }

    fun getApi(): UserApi {
        return userApi
    }
}
