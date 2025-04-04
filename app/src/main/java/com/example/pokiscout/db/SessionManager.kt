package com.example.pokiscout.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


val Context.dataStore by preferencesDataStore(name = "user_prefs")

object SessionManager {
    private val USER_KEY = stringPreferencesKey("logged_in_user")

    suspend fun saveLoggedInUser(context: Context, username: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_KEY] = username
        }
    }

    suspend fun getLoggedInUser(context: Context): String? {
        val prefs = context.dataStore.data.first()
        return prefs[USER_KEY]
    }

    suspend fun logoutUser(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.remove(USER_KEY)
        }
    }
}
