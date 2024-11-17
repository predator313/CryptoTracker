package com.example.cryptocurrencytracker.crypto.presentation.coin_list

import com.example.cryptocurrencytracker.crypto.presentation.model.CoinUi

sealed interface CoinListEvents {
    data class OnCoinClick(val coinUi: CoinUi): CoinListEvents
    data object OnRefresh: CoinListEvents
}