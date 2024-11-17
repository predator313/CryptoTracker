package com.example.cryptocurrencytracker.crypto.presentation.coin_list.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.cryptocurrencytracker.core.presentation_util.toString
import com.example.cryptocurrencytracker.crypto.presentation.coin_list.CoinListErrorEvents
import com.example.cryptocurrencytracker.crypto.presentation.coin_list.CoinListState
import com.example.cryptocurrencytracker.crypto.presentation.model.toCoinUi
import com.example.cryptocurrencytracker.ui.theme.CryptoCurrencyTrackerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext

@Composable
fun CoinListScreen(
    coinListState: CoinListState,
    modifier: Modifier = Modifier,
) {

    if (coinListState.isLoading) {
        Box(
            modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(coinListState.coins) { coinUi ->
            CoinListItem(coinUi = coinUi,
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            )
            HorizontalDivider(thickness = 1.dp)
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListScreenPreview() {
    CryptoCurrencyTrackerTheme {
        CoinListScreen(
            coinListState = CoinListState(
                coins = (1..100).map {
                    previewCoin.toCoinUi().copy(
                        id = it.toString()
                    )
                }
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}