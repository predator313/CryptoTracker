package com.example.cryptocurrencytracker.crypto.data.mapper

import com.example.cryptocurrencytracker.crypto.data.remote.CoinDto
import com.example.cryptocurrencytracker.crypto.domain.model.Coin

fun CoinDto.toCoin() :Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        priceUsd = priceUsd,
        marketCapUsd = marketCapUsd,
        symbol = symbol,
        changePercent24Hr = changePercent24Hr
    )
}