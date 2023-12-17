package com.fish.monsters.features.history.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.R
import com.fish.monsters.common.extensions.roundToString
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.AwardsCount
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.core.theme.DangerColor
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12
import com.fish.monsters.core.theme.EasyColor
import com.fish.monsters.core.theme.LightPrimaryColor
import com.fish.monsters.features.history.HistoryViewModel
import com.fish.monsters.features.history.ui.components.EnhancementHistoryRow
import com.fish.monsters.features.history.ui.components.HistoryDetailsSummary
import com.fish.monsters.features.history.ui.components.HistoryMap
import com.fish.monsters.features.history.ui.components.HistoryStartEndRow
import com.google.android.gms.maps.model.LatLng
import org.koin.compose.koinInject
import java.util.Locale

@Composable
fun HistoryDetailsScreen(contestId: Long, viewModel: HistoryViewModel = koinInject()) {
    viewModel.getContestDetailsById(contestId)

    val contestDetailsState = remember(viewModel.selectedContestDetailsState) {
        viewModel.selectedContestDetailsState
    }

    val contestDetails = contestDetailsState.value

    ScreenBox(title = stringResource(id = R.string.contest_details)) {
        HistoryDetailsScreenContent(contestDetails.selectedContestDetails)
    }
}

@Composable
fun HistoryDetailsScreenContent(
    contestDetails: Contest?,
    shape: PartiallyCutCornerShape = PartiallyCutCornerShape(
        DpSize(12.dp, 31.dp)
    ),
) {
    val context = LocalContext.current
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses = contestDetails?.gameLocation?.let {
        geocoder.getFromLocation(
            it.latitude,
            contestDetails.gameLocation.longitude,
            1
        )
    }
    val streetAndCity = parseStreetAndCity(addresses)

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
                    text = stringResource(id = R.string.contest_result),
                    color = LightPrimaryColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (contestDetails.isGameSuccess) {
                    Text(
                        text = " " + stringResource(id = R.string.success),
                        color = EasyColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                } else {
                    Text(
                        text = " " + stringResource(id = R.string.failure),
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val clipboardManager =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText(
                        "Location",
                        "${contestDetails.gameLocation.latitude}, ${contestDetails.gameLocation.longitude}"
                    )
                    clipboardManager.setPrimaryClip(clip)
                    if (android.os.Build.VERSION.SDK_INT < 33) {
                        Toast
                            .makeText(context, R.string.location_copied, Toast.LENGTH_SHORT)
                            .show()
                    }
                }, horizontalArrangement = Arrangement.Center) {
                Text(
                    text = contestDetails.gameLocation.latitude.roundToString(5) + ", " +
                            contestDetails.gameLocation.longitude.roundToString(5)
                )
            }
            if (contestDetails.enhancementsUsed.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .padding(0.dp)
                        .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.buff_icon),
                        contentDescription = "Buff Icon",
                        Modifier.size(20.dp),
                        tint = DarkPrimaryColor
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = stringResource(id = R.string.enhancements_history),
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Column(modifier = Modifier.fillMaxWidth(0.95f)) {
                    HistoryStartEndRow(
                        title = stringResource(id = R.string.history_game_start),
                        isEndRow = false
                    )

                    var previousTimeInSeconds = 0

                    contestDetails.enhancementsUsed.forEach { enhancement ->
                        val timeInSeconds = enhancement.time.toSeconds()
                        val space = spaceFromTime(timeInSeconds - previousTimeInSeconds)
                        EnhancementHistoryRow(
                            enhancement = enhancement,
                            height = space
                        )
                        previousTimeInSeconds = timeInSeconds
                    }

                    HistoryStartEndRow(
                        title = stringResource(id = R.string.history_game_end),
                        isEndRow = true,
                        spacerHeight = spaceFromTime(contestDetails.duration.toSeconds() - previousTimeInSeconds)
                    )
                }
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

private fun parseStreetAndCity(addresses: List<Address>?): String {
    return addresses?.firstOrNull()?.let { address ->
        val street = address.thoroughfare ?: ""
        val city = address.locality ?: ""
        listOf(street, city).filter { it.isNotBlank() }.joinToString(", ")
    } ?: "Unknown Place"
}

@Preview
@Composable
private fun HistoryDetailsScreenPreview() {
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
                    name = "Enhancement 4",
                    time = Duration(1, 30, 12)
                )
            ),
            bypassedMonsters = 18,
            awardsEarned = listOf(
                AwardsCount(Award.Grass, 2),
                AwardsCount(Award.Flower, 12),
                AwardsCount(Award.Pumpkin, 1)
            ),
            isGameSuccess = true,
            gameLocation = GameLocation(
                latitude = 25.0,
                longitude = 20.0
            )
        )
    )
}
