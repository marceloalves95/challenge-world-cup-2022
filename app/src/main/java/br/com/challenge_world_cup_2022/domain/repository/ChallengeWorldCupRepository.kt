package br.com.challenge_world_cup_2022.domain.repository

import kotlinx.coroutines.flow.Flow
import br.com.challenge_world_cup_2022.domain.models.MatchDomain

interface ChallengeWorldCupRepository {
    suspend fun getAllMatch(): Flow<List<MatchDomain>>
    suspend fun enableNotificationFor(id: String)
    suspend fun disableNotificationFor(id: String)
}