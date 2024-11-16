package com.example.cryptocurrencytracker.core.network_util

import com.example.cryptocurrencytracker.core.domain_util.NetworkError
import com.example.cryptocurrencytracker.core.domain_util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T,NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        //generally when client is not connected to internet
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        //this may cause problem to the kotlin cancellation exception
        //inside suspend function it is always the bad practice to e: Exception
        //so always add ensure active
        coroutineContext.ensureActive()

        return Result.Error(NetworkError.UNKNOWN_ERROR)
    }
    return responseToResult(response)
}