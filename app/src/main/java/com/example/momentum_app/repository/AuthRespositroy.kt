//package com.example.momentum_app.repository
//
//import android.content.Context
//import com.example.momentum_app.api.UserApi
//import com.example.momentum_app.jwttoken.TokenManager
//import com.example.momentum_app.model.UserLogin
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class AuthRepository(private val api: UserApi.ApiService, private val context: Context) {
//
//    suspend fun login(userLogin: UserLogin): Boolean {
//        return withContext(Dispatchers.IO) {
//            val response = api.loginUser(userLogin)
//            if (response.isSuccessful) {
//                val token = response.body()?.token
//                if (!token.isNullOrEmpty()) {
//                    TokenManager(context).saveToken(token)
//                    true
//                } else false
//            } else {
//                false
//            }
//        }
//    }
//}
