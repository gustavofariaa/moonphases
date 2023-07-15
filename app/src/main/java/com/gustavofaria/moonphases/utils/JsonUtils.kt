package com.gustavofaria.moonphases.utils

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type


object JsonUtils {

    inline fun <reified T> readJsonFromResources(resources: Resources, resourceId: Int): List<T> {
        val inputStream = resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))

        val gson = Gson()
        val jsonArray = gson.fromJson(reader, JsonArray::class.java)
        val typeMyType: Type = object : TypeToken<ArrayList<T?>?>() {}.type

        return gson.fromJson(jsonArray, typeMyType)
    }
}
