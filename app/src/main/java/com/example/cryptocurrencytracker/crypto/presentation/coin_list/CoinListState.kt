package com.example.cryptocurrencytracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.example.cryptocurrencytracker.crypto.presentation.model.CoinUi

@Immutable   //this protect un necessary recomposition
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selectedCoin: CoinUi ? = null
)
