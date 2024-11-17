package com.example.cryptocurrencytracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencytracker.core.domain_util.onError
import com.example.cryptocurrencytracker.core.domain_util.onSuccess
import com.example.cryptocurrencytracker.crypto.domain.repository.CoinRepository
import com.example.cryptocurrencytracker.crypto.presentation.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(
    private val coinRepository: CoinRepository
): ViewModel() {
    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart {
            loadCoins()
        }
        .stateIn(
           scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CoinListState()

        )
    private val _errorEvents = Channel<CoinListErrorEvents>()  //Channel are used for one time events generally like shared flow
    val errorEvents = _errorEvents.receiveAsFlow()

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            coinRepository
                .getCoins()
                .onSuccess {coins->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { coin->
                                coin.toCoinUi()
                            }
                        )
                    }
                }
                .onError {error->
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                    _errorEvents.send(CoinListErrorEvents.Error(error = error ))
                }
        }
    }
    fun onEvents(events: CoinListEvents) {
        when(events){
            is CoinListEvents.OnCoinClick ->{}
            is CoinListEvents.OnRefresh ->{}
        }
    }


}