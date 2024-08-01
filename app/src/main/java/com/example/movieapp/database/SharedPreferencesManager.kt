package com.example.movieapp.database

import android.content.Context

object SharedPreferenceManager {

    private const val PREF_NAME = "user_prefs"
    private const val KEY_USER_ID = "user_id"

    fun saveUserId(context: Context, userId: Int) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putInt(KEY_USER_ID, userId)
            apply()
        }
    }

    fun getUserId(context: Context): Int? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return if (prefs.contains(KEY_USER_ID)) {
            prefs.getInt(KEY_USER_ID, -1).takeIf { it != -1 }
        } else {
            null
        }
    }
}
