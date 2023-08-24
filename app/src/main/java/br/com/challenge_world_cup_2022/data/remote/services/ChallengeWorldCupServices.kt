package br.com.challenge_world_cup_2022.data.remote.services

import br.com.challenge_world_cup_2022.data.remote.model.MatchResponse
import retrofit2.Response
import retrofit2.http.GET

interface ChallengeWorldCupServices {
    @GET("api.json")
    suspend fun getAllMatch():List<MatchResponse>
}