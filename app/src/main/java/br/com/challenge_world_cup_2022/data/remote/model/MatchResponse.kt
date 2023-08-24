package br.com.challenge_world_cup_2022.data.remote.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("stadium")
    val stadium: StadiumResponse,
    @SerializedName("team1")
    val team1: String,
    @SerializedName("team2")
    val team2: String,
    @SerializedName("date")
    val date: String
) : Parcelable

@Parcelize
data class StadiumResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String
) : Parcelable
