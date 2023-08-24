package br.com.challenge_world_cup_2022.data.remote.source

import br.com.challenge_world_cup_2022.data.remote.mapper.toMatch
import br.com.challenge_world_cup_2022.data.remote.services.ChallengeWorldCupServices
import br.com.challenge_world_cup_2022.data.source.DataSource
import br.com.challenge_world_cup_2022.domain.models.MatchDomain
import br.com.challenge_world_cup_2022.extensions.network.getOrThrowDomainError

class DataSourceRemoteImpl(
    private val service: ChallengeWorldCupServices
) : DataSource.Remote {
    override suspend fun getAllMatch(): List<MatchDomain> {
        return runCatching {
            service.getAllMatch()
        }.getOrThrowDomainError().toMatch()
    }
}