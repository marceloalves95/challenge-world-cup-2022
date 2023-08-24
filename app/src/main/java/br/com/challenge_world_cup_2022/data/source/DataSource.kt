package br.com.challenge_world_cup_2022.data.source

import br.com.challenge_world_cup_2022.domain.models.MatchDomain
import kotlinx.coroutines.flow.Flow

sealed interface DataSource {
    interface Local : DataSource {
        fun getActiveNotificationIds(): Flow<Set<String>>
        suspend fun enableNotificationFor(id: String)
        suspend fun disableNotificationFor(id: String)
    }

    interface Remote : DataSource {
        suspend fun getAllMatch(): List<MatchDomain>
    }
}