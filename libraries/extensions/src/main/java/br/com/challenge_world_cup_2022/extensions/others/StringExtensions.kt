package br.com.challenge_world_cup_2022.extensions.others

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

const val CODE = 127397

fun String.getFlagCountry(): String {
    return this.map {
        String(Character.toChars(it.code + CODE))
    }.joinToString("")
}

fun String.showCountryName(): String = Locale("", this).isO3Country

fun String.getData(): String {
    val localDateTime = LocalDateTime.parse(this.removeSuffix("Z"))
    return DateTimeFormatter.ofPattern("dd/MM HH:mm").format(localDateTime)
}