package com.example.cryptocurrencytracker.crypto.domain.repository

import com.example.cryptocurrencytracker.core.domain_util.NetworkError
import com.example.cryptocurrencytracker.core.domain_util.Result
import com.example.cryptocurrencytracker.crypto.domain.model.Coin
import com.example.cryptocurrencytracker.crypto.domain.model.CoinPrice
import java.time.ZonedDateTime

interface CoinRepository {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>,NetworkError>
}