package br.com.challenge_world_cup_2022.domain.usecases.remote

import br.com.challenge_world_cup_2022.domain.repository.ChallengeWorldCupRepository
import br.com.challenge_world_cup_2022.extensions.others.executeFlow

class GetAllMatchUseCase(
    private val repository: ChallengeWorldCupRepository
) {
    suspend operator fun invoke() = executeFlow(
        getRepository = {
            repository.getAllMatch()
        }
    )
}