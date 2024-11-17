package com.example.cryptocurrencytracker.crypto.data.repository

import com.example.cryptocurrencytracker.core.domain_util.NetworkError
import com.example.cryptocurrencytracker.core.domain_util.Result
import com.example.cryptocurrencytracker.core.domain_util.map
import com.example.cryptocurrencytracker.core.network_util.constructUrl
import com.example.cryptocurrencytracker.core.network_util.safeCall
import com.example.cryptocurrencytracker.crypto.data.mapper.toCoin
import com.example.cryptocurrencytracker.crypto.data.remote.CoinsResponseDto
import com.example.cryptocurrencytracker.crypto.domain.model.Coin
import com.example.cryptocurrencytracker.crypto.domain.repository.CoinRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class CoinRepositoryImp(
    private val httpClient: HttpClient
): CoinRepository {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map {
            it.data.map { coinDto ->
                coinDto.toCoin()
            }
        }
    }
}