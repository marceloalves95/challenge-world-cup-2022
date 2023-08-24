package br.com.challenge_world_cup_2022.network.connectivity

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Status>
    enum class Status(val status: String) {
        AVAILABLE("Available"),
        UNAVAILABLE("Unavailable"),
        LOSING("Losing"),
        LOST("Lost")
    }
}