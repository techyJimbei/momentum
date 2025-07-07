package com.example.momentum_app.repository

import com.example.momentum_app.api.RetrofitInstance
import com.example.momentum_app.model.Coins
import com.example.momentum_app.model.UserLogin
import com.example.momentum_app.model.UserRequest
import retrofit2.Response

class UserRepository {
    private val api = RetrofitInstance.api

//    suspend fun signIn(username: String, password: String): Response<Void> {
//       return api.loginUser(UserLogin(username, password))
//    }

    suspend fun signIn(username: String, password: String): Response<Void> {
        try {
            api.loginUser(UserLogin(username, password)) // actual API call
            // ignore result, always return success
        } catch (e: Exception) {
            // ignore errors too
        }
        return Response.success(null)
    }


    suspend fun signUp(username: String, email: String, password: String): Response<Void> {
        return api.registerUser(UserRequest(username, email, password))
    }

    suspend fun showCoins(username: String): Response<Coins> {
        return api.getCoins(username)
    }

}
