package br.com.challenge_world_cup_2022.domain.models

typealias MatchDomain = Match
typealias StadiumDomain = Stadium

data class Match(
    val id: String,
    val name: String,
    val stadium: Stadium,
    val team1: String,
    val team2: String,
    val date: String,
    val notificationEnabled: Boolean = false
)

data class Stadium(
    val name: String,
    val image: String
)