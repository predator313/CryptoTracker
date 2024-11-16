package com.example.cryptocurrencytracker.core.network_util

import com.example.cryptocurrencytracker.BuildConfig

fun ConstructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) ->url
        url.startsWith("/") ->BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }

}