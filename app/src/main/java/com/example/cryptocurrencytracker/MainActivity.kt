package com.example.cryptocurrencytracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cryptocurrencytracker.core.presentation_util.ObserveAsErrorEvents
import com.example.cryptocurrencytracker.core.presentation_util.toString
import com.example.cryptocurrencytracker.crypto.presentation.coin_list.CoinListErrorEvents
import com.example.cryptocurrencytracker.crypto.presentation.coin_list.CoinListViewModel
import com.example.cryptocurrencytracker.crypto.presentation.coin_list.components.CoinListScreen
import com.example.cryptocurrencytracker.ui.theme.CryptoCurrencyTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoCurrencyTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state = viewModel.state.collectAsStateWithLifecycle()
                    val context = LocalContext.current
                    ObserveAsErrorEvents(
                        errorEvents = viewModel.errorEvents
                    ){
                        when(it) {
                            is CoinListErrorEvents.Error -> {
                                Toast.makeText(
                                    context,
                                    it.error.toString(context),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    CoinListScreen(
                        coinListState = state.value,
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

