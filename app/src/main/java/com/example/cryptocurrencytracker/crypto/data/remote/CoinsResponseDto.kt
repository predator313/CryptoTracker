package com.example.cryptocurrencytracker.crypto.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(
    val data: List<CoinDto>
)
