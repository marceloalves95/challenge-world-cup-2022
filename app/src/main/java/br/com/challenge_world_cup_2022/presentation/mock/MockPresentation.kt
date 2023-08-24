package br.com.challenge_world_cup_2022.presentation.mock

import br.com.challenge_world_cup_2022.domain.models.Match
import br.com.challenge_world_cup_2022.domain.models.Stadium

val dummyStadium = Stadium(
    name = "LUSAIL",
    image = "https://digitalinnovationone.github.io/copa-2022-android/statics/lusali-stadium.png"
)

val dummyMatch = Match(
    id = "1",
    name = "1ª RODADA",
    stadium = dummyStadium,
    team1 = "BR",
    team2 = "RS",
    date = "2022-11-24T19:00:00Z"
)

val dummyListMatch: List<Match> = listOf(
    Match(
        id = "1",
        name = "1ª RODADA",
        stadium = Stadium(
            name = "LUSAIL",
            image = "https://digitalinnovationone.github.io/copa-2022-android/statics/lusali-stadium.png"
        ),
        team1 = "BR",
        team2 = "RS",
        date = "2022-11-24T19:00:00Z"
    ),
    Match(
        id = "2",
        name = "2ª RODADA",
        stadium = Stadium(
            name = "ESTÁDIO 974",
            image = "https://digitalinnovationone.github.io/copa-2022-android/statics/974-stadium.png"
        ),
        team1 = "BR",
        team2 = "CH",
        date = "2022-11-28T16:00:00Z"
    ),
    Match(
        id = "3",
        name = "3ª RODADA",
        stadium = Stadium(
            name = "LUSAIL",
            image = "https://digitalinnovationone.github.io/copa-2022-android/statics/lusali-stadium.png"
        ),
        team1 = "CM",
        team2 = "BR",
        date = "2022-12-02T19:00:00Z"
    ),
    Match(
        id = "4",
        name = "Semi Final",
        stadium = Stadium(
            name = "LUSAIL",
            image = "https://digitalinnovationone.github.io/copa-2022-android/statics/lusali-stadium.png"
        ),
        team1 = "BR",
        team2 = "AR",
        date = "2022-10-20T02:08:00Z"
    ),
    Match(
        id = "5",
        name = "FINAL",
        stadium = Stadium(
            name = "LUSAIL",
            image = "https://digitalinnovationone.github.io/copa-2022-android/statics/lusali-stadium.png"
        ),
        team1 = "BR",
        team2 = "FR",
        date = "2022-10-20T02:14:00Z"
    )
)