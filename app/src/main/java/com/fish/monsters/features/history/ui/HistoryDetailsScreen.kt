package com.fish.monsters.features.history.ui

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.R
import com.fish.monsters.common.extensions.roundToString
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.AwardType
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.core.theme.DangerColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12
import com.fish.monsters.core.theme.EasyColor
import com.fish.monsters.core.theme.LightPrimaryColor
import com.fish.monsters.features.history.HistoryViewModel
import com.fish.monsters.features.history.ui.components.EnhancementsHistory
import com.fish.monsters.features.history.ui.components.HistoryDetailsSummary
import com.fish.monsters.features.history.ui.components.HistoryMap
import com.fish.monsters.utils.copyLocationToClipboard
import com.google.android.gms.maps.model.LatLng
import org.koin.compose.koinInject
import java.util.Locale

@Composable
fun HistoryDetailsScreen(contestId: Long, viewModel: HistoryViewModel = koinInject()) {

    LaunchedEffect(true) {
        viewModel.getContestDetailsById(contestId)
    }

    val contestDetails = viewModel.historyState.value.selectedContest
    val context = LocalContext.current
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses = contestDetails?.gameLocation?.let {
        geocoder.getFromLocation(
            it.latitude,
            it.longitude,
            1
        )
    }
    val streetAndCity = parseStreetAndCity(addresses, context)

    ScreenBox(title = stringResource(id = R.string.contest_details)) {
        HistoryDetailsScreenContent(contestDetails, streetAndCity)
    }
}

@Composable
fun HistoryDetailsScreenContent(
    contestDetails: Contest,
    streetAndCity: String,
    shape: PartiallyCutCornerShape = PartiallyCutCornerShape(
        DpSize(12.dp, 31.dp)
    ),
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 9.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (contestDetails != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(74.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.contest_result).plus(" "),
                    color = LightPrimaryColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (contestDetails.isGameSuccess) {
                    Text(
                        text = stringResource(id = R.string.success),
                        color = EasyColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.failure),
                        color = DangerColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkPrimaryColorA12, shape)
                    .padding(vertical = 28.dp),

            ) {
                HistoryDetailsSummary(contestDetails)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(text = streetAndCity)
            }
            Spacer(modifier = Modifier.height(7.dp))
            HistoryMap(
                LatLng(
                    contestDetails.gameLocation.latitude,
                    contestDetails.gameLocation.longitude
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        copyLocationToClipboard(
                            context,
                            context.getString(R.string.location),
                            contestDetails.gameLocation.latitude.roundToString(5) + ", " + contestDetails.gameLocation.longitude.roundToString(
                                5
                            )
                        )
                    },
                textAlign = TextAlign.Center,
                text = contestDetails.gameLocation.latitude.roundToString(5) + ", " +
                        contestDetails.gameLocation.longitude.roundToString(5)
            )
            if (contestDetails.enhancementsUsed.isNotEmpty()) {
                EnhancementsHistory(contestDetails)
            }
        }
    }
}

fun spaceFromTime(timeInSeconds: Int): Dp {
    val secondsPerDp = 60f

    return when {
        timeInSeconds < 600 -> 10.dp
        timeInSeconds > 5400 -> 90.dp
        else -> (timeInSeconds / secondsPerDp).dp
    }
}

private fun parseStreetAndCity(addresses: List<Address>?, context: Context): String {
    return addresses?.firstOrNull()?.let { address ->
        val street = address.thoroughfare ?: ""
        val city = address.locality ?: ""
        listOf(street, city).filter { it.isNotBlank() }.joinToString(", ")
    } ?: context.getString(R.string.unknown_place)
}

@Preview
@Composable
private fun HistoryDetailsScreenPreview() {
    PreviewContainer {
        HistoryDetailsScreenContent(
            Contest(
                id = 4,
                date = "10 march 2024",
                duration = Duration(1, 15, 40),
                points = 75,
                difficultyLevel = DifficultyLevel.MEDIUM,
                rewardsCount = 12,
                enhancementsUsed = listOf(
                    Enhancement(
                        name = "kraken_urine",
                        time = Duration(1, 30, 12)
                    )
                ),
                bypassedMonsters = 18,
                awardsEarned = listOf(
                    Award(AwardType.Grass, 2),
                    Award(AwardType.Flower, 12),
                    Award(AwardType.Pumpkin, 1)
                ),
                isGameSuccess = true,
                gameLocation = GameLocation(
                    latitude = 25.0,
                    longitude = 20.0
                )
            ), "Poznan, Koscielna"
        )
    }
}
