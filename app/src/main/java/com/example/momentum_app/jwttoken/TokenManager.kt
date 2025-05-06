//package com.example.momentum_app.jwttoken
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.*
//import androidx.datastore.preferences.preferencesDataStore
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//
//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")
//
//class TokenManager(private val context: Context) {
//
//    companion object {
//        val TOKEN_KEY = stringPreferencesKey("jwt_token")
//    }
//
//    suspend fun saveToken(token: String) {
//        context.dataStore.edit { preferences ->
//            preferences[TOKEN_KEY] = token
//        }
//    }
//
//    val token: Flow<String?> = context.dataStore.data
//        .map { preferences -> preferences[TOKEN_KEY] }
//}
