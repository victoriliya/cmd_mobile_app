package com.example.cmd.Utilities

import android.content.Context
import com.android.volley.toolbox.Volley

class SharedPref(context: Context) {

    val PREFS_FILENAME = "prefs"
    val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)

    val IS_LOGGED_IN = "isLoggedIn"
    val AUTH_TOKEN = "authToken"
    val USER_ID = "userId"

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(IS_LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(IS_LOGGED_IN, value).apply()

    var authToken: String?
        get() = prefs.getString(AUTH_TOKEN, "")
        set(value) = prefs.edit().putString(AUTH_TOKEN, value).apply()

    var userId: String?
        get() = prefs.getString(USER_ID, "")
        set(value) = prefs.edit().putString(USER_ID, value).apply()

    val requestQueue = Volley.newRequestQueue(context)
}
