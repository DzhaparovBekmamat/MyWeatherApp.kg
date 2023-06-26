package com.example.weatherappkg.core

/**
 * Author: Dzhaparov Bekmamat
 */
sealed class UIState<T>(
    val data: T? = null, val message: String? = null
) {
    class Loading<T> : UIState<T>()
    class Success<T>(data: T?) : UIState<T>(data = data)
    class Error<T>(message: String?) : UIState<T>(message = message)
}
