package com.example.momentum_app.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momentum_app.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SignUpViewModel : ViewModel() {
    private val repository = UserRepository()
    private val _coinCount = MutableStateFlow<Int?>(null)
    val coinCount: StateFlow<Int?> = _coinCount

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


            if (login.isSuccessful)
            {
                saveUsername(context, username)
                onResult(true, "User logged in successfully")
            } else
            {
                onResult(true, "Failed to login user")
            }
        }
    }

    fun getCoins(username: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.showCoins(username)
                Log.d("CoinsResponse", "Coins: ${response.body()?.coins}")
                if (response.isSuccessful) {
                    val coinsDTO = response.body()
                    _coinCount.value = coinsDTO?.coins
                    onResult(true)
                } else {
                    onResult(false)
                }

            } catch (e: Exception) {
                onResult(false)
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

