package com.example.momentum_app.api

import android.content.Context
//import com.example.momentum_app.jwttoken.AuthInterceptor
//import com.example.momentum_app.jwttoken.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

    fun provideRetrofit(context: Context): Retrofit {
//        val tokenManager = TokenManager(context)
        val client = OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor(tokenManager))
            .build()

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

