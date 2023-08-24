package br.com.challenge_world_cup_2022.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.challenge_world_cup_2022.R
import br.com.challenge_world_cup_2022.domain.models.Match
import br.com.challenge_world_cup_2022.extensions.others.getData
import br.com.challenge_world_cup_2022.extensions.others.getFlagCountry
import br.com.challenge_world_cup_2022.extensions.others.showCountryName
import br.com.challenge_world_cup_2022.presentation.mock.dummyMatch
import br.com.challenge_world_cup_2022.presentation.theme.large
import br.com.challenge_world_cup_2022.presentation.ui.NotificationOnClick
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CardMatchItem(
    match: Match,
    onNotificationClick: NotificationOnClick
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = large,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(match.stadium.image)
                    .crossfade(true)
                    .placeholder(R.drawable.lusali_stadium)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    val drawable = if (match.notificationEnabled) R.drawable.ic_notifications_active
                    else R.drawable.ic_notifications
                    Image(
                        painter = painterResource(id = drawable),
                        modifier = Modifier.clickable {
                            onNotificationClick(match)
                        },
                        contentDescription = null
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${match.date.getData()} - ${match.name}",
                        style = MaterialTheme.typography.headlineSmall.copy(color = Color.White)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TeamItem(team = match.team1)
                    Text(
                        text = "X",
                        modifier = Modifier.padding(end = 16.dp, start = 16.dp),
                        style = MaterialTheme.typography.headlineSmall.copy(color = Color.White)
                    )
                    TeamItem(team = match.team2)
                }
            }
        }
    }
}

@Composable
fun TeamItem(team: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = team.getFlagCountry(),
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.headlineSmall.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = team.showCountryName(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall.copy(color = Color.White)
        )
    }
}

@Preview
@Composable
fun CardMatchItemPreview() {
    CardMatchItem(dummyMatch) {}
}