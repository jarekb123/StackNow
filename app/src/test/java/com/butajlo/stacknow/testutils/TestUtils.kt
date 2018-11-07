package com.butajlo.stacknow.testutils

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

val GSON = Gson()

inline fun <reified T : Any> parseJson(filePath: String): T = GSON.fromJson(getFileString(filePath), T::class.java)

fun getFileString(filePath: String): String {
    val sb = StringBuilder()
    // loading resources from 'resources' directory
    val reader = BufferedReader(InputStreamReader(object {}.javaClass.classLoader?.getResourceAsStream(filePath)))

    var line = reader.readLine()
    while (line != null) {
        sb.append(line)
        line = reader.readLine()
    }

    return sb.toString()
}