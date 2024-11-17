package com.example.cryptocurrencytracker.di

import com.example.cryptocurrencytracker.core.network_util.HttpClientFactory
import com.example.cryptocurrencytracker.crypto.data.repository.CoinRepositoryImp
import com.example.cryptocurrencytracker.crypto.domain.repository.CoinRepository
import com.example.cryptocurrencytracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClientFactory.create(CIO.create())
    }

    singleOf(::CoinRepositoryImp).bind<CoinRepository>()
    viewModelOf(::CoinListViewModel)
}