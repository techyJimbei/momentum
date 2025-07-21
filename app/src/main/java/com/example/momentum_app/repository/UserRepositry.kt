package com.example.momentum_app.repository

import com.example.momentum_app.api.RetrofitInstance
import com.example.momentum_app.model.Coins
import com.example.momentum_app.model.LoginResponse
import com.example.momentum_app.model.UserLogin
import com.example.momentum_app.model.UserRequest
import com.example.momentum_app.model.VerifyResponse
import retrofit2.Response

class UserRepository {
    private val api = RetrofitInstance.api

//    suspend fun signIn(username: String, password: String): Response<Void> {
//       return api.loginUser(UserLogin(username, password))
//    }

    suspend fun signIn(username: String, password: String): Response<Map<String, String>> {
        return try {
            RetrofitInstance.api.loginUser(UserLogin(username, password))
        } catch (e: Exception) {
            e.printStackTrace()
            Response.error(500, okhttp3.ResponseBody.create(null, ""))
        }
    }



    suspend fun signUp(username: String, email: String, password: String): Response<Void> {
        return api.registerUser(UserRequest(username, email, password))
    }

    suspend fun showCoins(username: String): Response<Coins> {
        return api.getCoins(username)
    }

    suspend fun verifyToken(token: String): VerifyResponse? {
        return try {
            val response = api.verify(mapOf("token" to token))
            if (response.isSuccessful) {
                response.body()
            } else null
        } catch (e: Exception) {
            null
        }
    }


}
