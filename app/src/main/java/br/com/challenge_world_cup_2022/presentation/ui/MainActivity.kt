package br.com.challenge_world_cup_2022.presentation.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.challenge_world_cup_2022.extensions.others.observe
import br.com.challenge_world_cup_2022.network.connectivity.ConnectivityObserver
import br.com.challenge_world_cup_2022.network.connectivity.NetworkConnectivityObserver
import br.com.challenge_world_cup_2022.presentation.components.RuntimePermissionsDialog
import br.com.challenge_world_cup_2022.presentation.components.Screen
import br.com.challenge_world_cup_2022.presentation.model.ChallengeWorldCupState
import br.com.challenge_world_cup_2022.presentation.model.MainUIState
import br.com.challenge_world_cup_2022.presentation.theme.Challengeworldcup2022Theme
import br.com.challenge_world_cup_2022.worker.NotificationMatcherWorker
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeActions()
        setContent {
            ContentScreen()
        }
    }

    private fun observeActions() {
        mainViewModel.action.observe(this) { action ->
            when (action) {
                is MainUIState.DisableNotification ->
                    NotificationMatcherWorker.cancel(applicationContext, action.match)

                is MainUIState.EnableNotification ->
                    NotificationMatcherWorker.start(applicationContext, action.match)
            }
        }
    }

    @Composable
    fun ContentScreen() {

        TestConnectivity()
        val state by mainViewModel.stateMatch.collectAsState()

        Challengeworldcup2022Theme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Screen {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        RuntimePermissionsDialog(
                            Manifest.permission.POST_NOTIFICATIONS,
                            onPermissionDenied = {},
                            onPermissionGranted = {},
                        )
                    }
                    LoadMatchState(state)
                }
            }
        }
    }

    @Composable
    fun TestConnectivity() {
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        val status by connectivityObserver.observe().collectAsState(
            initial = ConnectivityObserver.Status.UNAVAILABLE
        )
        mainViewModel.testConnectivity(status)
    }

    @Composable
    fun LoadMatchState(state: ChallengeWorldCupState) {
        when (state) {
            is ChallengeWorldCupState.ScreenData -> MatchScreenData(state.screenData) {
                mainViewModel.toggleNotification(it)
            }

            is ChallengeWorldCupState.Error -> MatchScreenError(state.exception?.message)
            is ChallengeWorldCupState.NoConnection -> MatchScreenNoConnection()
            else -> Unit
        }
    }
}