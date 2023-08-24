package br.com.challenge_world_cup_2022.data.remote.mapper

import br.com.challenge_world_cup_2022.data.remote.model.MatchResponse
import br.com.challenge_world_cup_2022.data.remote.model.StadiumResponse
import br.com.challenge_world_cup_2022.domain.models.MatchDomain
import br.com.challenge_world_cup_2022.domain.models.StadiumDomain

internal fun List<MatchResponse>.toMatch() = map { it.toMatch() }

internal fun MatchResponse.toMatch() = MatchDomain(
    id = "$team1-$team2",
    name = name,
    stadium = stadium.toStadium(),
    team1 = team1,
    team2 = team2,
    date = date
)

internal fun StadiumResponse.toStadium() =
    StadiumDomain(
        name = name, image = image
    )