package br.com.challenge_world_cup_2022.domain.usecases.local

import br.com.challenge_world_cup_2022.domain.repository.ChallengeWorldCupRepository

class EnableNotificationUseCase(
    private val repository: ChallengeWorldCupRepository
) {
    suspend operator fun invoke(id: String) {
        repository.enableNotificationFor(id)
    }
}