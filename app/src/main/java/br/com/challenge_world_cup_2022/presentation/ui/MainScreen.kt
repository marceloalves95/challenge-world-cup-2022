package br.com.challenge_world_cup_2022.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.challenge_world_cup_2022.R
import br.com.challenge_world_cup_2022.domain.models.Match
import br.com.challenge_world_cup_2022.domain.models.MatchDomain
import br.com.challenge_world_cup_2022.presentation.components.CardMatchItem
import br.com.challenge_world_cup_2022.presentation.components.MessageError
import br.com.challenge_world_cup_2022.presentation.components.Screen
import br.com.challenge_world_cup_2022.presentation.mock.dummyListMatch

typealias NotificationOnClick = (match: MatchDomain) -> Unit

@Composable
fun MatchScreenData(listMatch: List<Match>, onNotificationClick: NotificationOnClick) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = listMatch,
            itemContent = { content ->
                CardMatchItem(content, onNotificationClick)
            }
        )
    }
}

@Composable
fun MatchScreenError(message: String?) {
    MessageError {
        Text(
            text = message ?: stringResource(id = R.string.error),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MatchScreenNoConnection() {
    MessageError {
        Text(
            text = stringResource(id = R.string.no_connection),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MatchScreenDataPreview() {
    Screen {
        MatchScreenData(listMatch = dummyListMatch) {}
    }
}

@Preview(showBackground = true)
@Composable
fun MatchScreenErrorPreview() {
    Screen {
        MatchScreenError(Throwable().message)
    }
}

@Preview(showBackground = true)
@Composable
fun MatchScreenNoConnectionPreview() {
    Screen {
        MatchScreenNoConnection()
    }
}