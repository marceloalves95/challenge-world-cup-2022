package br.com.challenge_world_cup_2022.extensions.others

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun <T : ViewModel> T.launch(block: suspend T.() -> Unit) {
    viewModelScope.launch(Dispatchers.Main.immediate) {
        block()
    }
}