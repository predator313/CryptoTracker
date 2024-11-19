package com.example.cryptocurrencytracker.crypto.data.mapper

import com.example.cryptocurrencytracker.crypto.data.remote.CoinDto
import com.example.cryptocurrencytracker.crypto.data.remote.CoinPriceDto
import com.example.cryptocurrencytracker.crypto.domain.model.Coin
import com.example.cryptocurrencytracker.crypto.domain.model.CoinPrice
import java.time.Instant
import java.time.ZoneId

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

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.of("UTC"))
    )
}