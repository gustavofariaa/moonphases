package com.gustavofaria.moonphases.utils

import android.content.Context

object SharedPreferencesUtils {
    private const val SHARED_PREFERENCES_NAME = "com.gustavofaria.moonphases"
    private const val ALREADY_PASSED_ON_BOARDING = "already_passed_on_boarding"

    fun setAlreadyPassedOnBoarding(context: Context, value: Boolean) {
        val sharedPreferences = context.getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit().putBoolean(ALREADY_PASSED_ON_BOARDING, value).apply()
    }

    fun getAlreadyPassedOnBoarding(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getBoolean(ALREADY_PASSED_ON_BOARDING, false)
    }
}
