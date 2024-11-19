package com.example.cryptocurrencytracker.crypto.data.repository

import com.example.cryptocurrencytracker.core.domain_util.NetworkError
import com.example.cryptocurrencytracker.core.domain_util.Result
import com.example.cryptocurrencytracker.core.domain_util.map
import com.example.cryptocurrencytracker.core.network_util.constructUrl
import com.example.cryptocurrencytracker.core.network_util.safeCall
import com.example.cryptocurrencytracker.crypto.data.mapper.toCoin
import com.example.cryptocurrencytracker.crypto.data.mapper.toCoinPrice
import com.example.cryptocurrencytracker.crypto.data.remote.CoinHistoryResponseDto
import com.example.cryptocurrencytracker.crypto.data.remote.CoinsResponseDto
import com.example.cryptocurrencytracker.crypto.domain.model.Coin
import com.example.cryptocurrencytracker.crypto.domain.model.CoinPrice
import com.example.cryptocurrencytracker.crypto.domain.repository.CoinRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

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

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}