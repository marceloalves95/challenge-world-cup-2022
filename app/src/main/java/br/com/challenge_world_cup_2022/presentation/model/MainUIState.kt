package br.com.challenge_world_cup_2022.presentation.model

import br.com.challenge_world_cup_2022.domain.models.MatchDomain

sealed interface MainUIState {
    data class EnableNotification(val match: MatchDomain) : MainUIState
    data class DisableNotification(val match: MatchDomain) : MainUIState
}
