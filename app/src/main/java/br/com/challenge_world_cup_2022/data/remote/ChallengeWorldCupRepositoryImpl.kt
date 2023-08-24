package br.com.challenge_world_cup_2022.data.remote

import br.com.challenge_world_cup_2022.data.local.DataSourceLocalImpl
import br.com.challenge_world_cup_2022.data.remote.source.DataSourceRemoteImpl
import br.com.challenge_world_cup_2022.domain.models.MatchDomain
import br.com.challenge_world_cup_2022.domain.repository.ChallengeWorldCupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

class ChallengeWorldCupRepositoryImpl(
    private val dataSourceLocal: DataSourceLocalImpl,
    private val dataSourceRemote: DataSourceRemoteImpl
) : ChallengeWorldCupRepository {

    override suspend fun getAllMatch(): Flow<List<MatchDomain>> {
        return flowOf(dataSourceRemote.getAllMatch())
            .combine(dataSourceLocal.getActiveNotificationIds()) { matches: List<MatchDomain>, ids: Set<String> ->
                matches.map { match ->
                    match.copy(notificationEnabled = ids.contains(match.id))
                }
            }
    }

    override suspend fun enableNotificationFor(id: String) {
        dataSourceLocal.enableNotificationFor(id)
    }

    override suspend fun disableNotificationFor(id: String) {
        dataSourceLocal.disableNotificationFor(id)
    }
}