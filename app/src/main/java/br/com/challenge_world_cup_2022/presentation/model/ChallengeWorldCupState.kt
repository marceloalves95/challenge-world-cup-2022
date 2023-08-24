package br.com.challenge_world_cup_2022.presentation.model

import br.com.challenge_world_cup_2022.domain.models.MatchDomain

sealed class ChallengeWorldCupState {
    object Loading : ChallengeWorldCupState()
    data class ScreenData(val screenData: List<MatchDomain>) : ChallengeWorldCupState()
    data class Error(val exception: Throwable?) : ChallengeWorldCupState()
    object NoConnection : ChallengeWorldCupState()
}
