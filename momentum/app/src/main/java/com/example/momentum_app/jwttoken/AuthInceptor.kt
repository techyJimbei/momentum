//package com.example.momentum_app.jwttoken
//
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.runBlocking
//import okhttp3.Interceptor
//import okhttp3.Response
//
//class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val token = runBlocking { tokenManager.token.first() }
//        val request = if (!token.isNullOrBlank()) {
//            chain.request().newBuilder()
//                .addHeader("Authorization", "Bearer $token")
//                .build()
//        } else {
//            chain.request()
//        }
//        return chain.proceed(request)
//    }
//}
