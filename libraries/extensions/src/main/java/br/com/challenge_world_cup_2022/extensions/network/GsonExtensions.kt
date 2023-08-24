package br.com.challenge_world_cup_2022.extensions.network

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> setFromFile(assetManager: AssetManager, archiveJson: String): T {
    return Gson().fromFile(assetManager, archiveJson)
}

inline fun <reified T> Gson.fromFile(assetManager: AssetManager, filename: String): T {
    return fromJson(
        assetManager.open(filename).bufferedReader(),
        object : TypeToken<T>() {}.type
    )
}