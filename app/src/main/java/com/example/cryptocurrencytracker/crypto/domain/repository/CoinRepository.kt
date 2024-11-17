package com.example.cryptocurrencytracker.crypto.domain.repository

import com.example.cryptocurrencytracker.core.domain_util.NetworkError
import com.example.cryptocurrencytracker.core.domain_util.Result
import com.example.cryptocurrencytracker.crypto.domain.model.Coin

interface CoinRepository {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}