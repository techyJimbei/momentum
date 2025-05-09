package com.example.momentum_app.api

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // Create Gson instance with lenient mode
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create(gson)) // use lenient gson here
            .build()
    }

    val api: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }

    fun provideRetrofit(context: Context): Retrofit {
        val client = OkHttpClient.Builder()
            // .addInterceptor(AuthInterceptor(tokenManager)) // Uncomment if needed
            .build()

        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)) // use lenient gson here too
            .build()
    }
}