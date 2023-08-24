package br.com.challenge_world_cup_2022.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.challenge_world_cup_2022.domain.models.MatchDomain
import br.com.challenge_world_cup_2022.domain.usecases.local.DisableNotificationUseCase
import br.com.challenge_world_cup_2022.domain.usecases.local.EnableNotificationUseCase
import br.com.challenge_world_cup_2022.domain.usecases.remote.GetAllMatchUseCase
import br.com.challenge_world_cup_2022.extensions.others.launch
import br.com.challenge_world_cup_2022.network.connectivity.ConnectivityObserver
import br.com.challenge_world_cup_2022.network.event.Event
import br.com.challenge_world_cup_2022.presentation.model.ChallengeWorldCupState
import br.com.challenge_world_cup_2022.presentation.model.MainUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getAllMatchUseCase: GetAllMatchUseCase,
    private val enableNotificationUseCase: EnableNotificationUseCase,
    private val disableNotificationUseCase: DisableNotificationUseCase
) : ViewModel() {

    private val _stateMatch =
        MutableStateFlow<ChallengeWorldCupState>(ChallengeWorldCupState.Loading)
    val stateMatch: StateFlow<ChallengeWorldCupState> get() = _stateMatch

    private val _action = MutableSharedFlow<MainUIState>()
    val action: SharedFlow<MainUIState> = _action

    init {
        loadAllMatch()
    }

    private fun loadAllMatch() = launch {
        getAllMatchUseCase.invoke().collect { event ->
            when (event) {
                is Event.Loading -> {
                    _stateMatch.value = ChallengeWorldCupState.Loading
                }

                is Event.Data -> {
                    event.data.collect {
                        _stateMatch.value = ChallengeWorldCupState.ScreenData(it)
                    }
                }

                is Event.Error -> {
                    _stateMatch.value = ChallengeWorldCupState.Error(event.error)
                }
            }
        }
    }

    fun testConnectivity(status: ConnectivityObserver.Status) {
        when (status) {
            ConnectivityObserver.Status.LOST -> {
                _stateMatch.value = ChallengeWorldCupState.NoConnection
            }

            else -> {
                loadAllMatch()
            }
        }
    }


    fun toggleNotification(match: MatchDomain) {
        viewModelScope.launch {
            runCatching {
                withContext(Dispatchers.Main) {
                    val action = if (match.notificationEnabled) {
                        disableNotificationUseCase(match.id)
                        MainUIState.DisableNotification(match)
                    } else {
                        enableNotificationUseCase(match.id)
                        MainUIState.EnableNotification(match)
                    }
                    sendAction(action)
                }
            }
        }
    }

    private fun sendAction(action: MainUIState) = launch {
        _action.emit(action)
    }

}

