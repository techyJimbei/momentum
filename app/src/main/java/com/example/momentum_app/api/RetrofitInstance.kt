package com.example.momentum_app.api

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    // Logging interceptor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log full request/response bodies
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Add the logging interceptor
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.127.207:8080/")
            .client(client) // Use client with logging
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

    fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .client(client) // Reuse the same logging-enabled client
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
