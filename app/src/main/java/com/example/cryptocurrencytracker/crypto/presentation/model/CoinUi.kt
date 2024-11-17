package com.example.cryptocurrencytracker.crypto.presentation.model

import android.icu.text.NumberFormat
import androidx.annotation.DrawableRes
import com.example.cryptocurrencytracker.crypto.domain.model.Coin
import com.example.cryptocurrencytracker.core.presentation_util.getDrawableIdForCoin
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercentage24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,

)
data class DisplayableNumber(
    val value: Double,
    val formatted: String,
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercentage24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol = symbol)


    )
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumIntegerDigits = 2
        maximumFractionDigits = 2
    }
  return  DisplayableNumber(
      value = this,
      formatted = formatter.format(this)
    )
}
