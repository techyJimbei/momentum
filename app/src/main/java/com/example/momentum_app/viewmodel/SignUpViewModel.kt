package com.example.momentum_app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentum_app.repository.UserRepository
import kotlinx.coroutines.launch


class SignUpViewModel : ViewModel() {
    private val repository = UserRepository()

    fun registerUser(context: Context,username: String, email: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {

                val response = repository.signUp(username, email, password)
                saveUsername(context, username)

                if (response.isSuccessful) {
                    onResult(true, "User registered successfully")
                } else {
                    onResult(false, "Failed to register user")
                }

            } catch (e: Exception) {
                onResult(false, "Error: ${e.message}")
            }
        }
    }

    fun loginUser(context: Context,username: String, password: String, onResult: (Boolean, String) -> Unit){
        viewModelScope.launch {
            val login = repository.signIn(username, password)
            saveUsername(context, username)

            if (login.isSuccessful)
            {
                onResult(true, "User logged in successfully")
            } else
            {
                onResult(true, "Failed to login user")
            }
        }
    }
}
fun saveUsername(context: Context, username: String) {
    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString("username", username)
        apply()
    }
}

