package com.example.cryptocurrencytracker.crypto.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class CoinHistoryResponseDto(
    val data: List<CoinPriceDto>
)
