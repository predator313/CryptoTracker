package com.example.cryptocurrencytracker

import android.app.Application
import com.example.cryptocurrencytracker.di.appModule
import io.ktor.http.ContentType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoCurrencyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@CryptoCurrencyApp)
            androidLogger()
            modules(appModule)
        }
    }
}