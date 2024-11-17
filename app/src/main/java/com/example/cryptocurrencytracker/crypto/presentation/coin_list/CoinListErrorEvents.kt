package com.example.cryptocurrencytracker.crypto.presentation.coin_list

import com.example.cryptocurrencytracker.core.domain_util.NetworkError

sealed interface CoinListErrorEvents {
    data class Error(val error: NetworkError): CoinListErrorEvents
}