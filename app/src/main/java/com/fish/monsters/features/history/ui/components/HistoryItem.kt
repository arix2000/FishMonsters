package com.fish.monsters.features.history.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.extensions.drawNeonStroke
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.core.theme.DarkPrimaryColorA12
import org.koin.compose.koinInject

@Composable
fun HistoryItem(
    contestInfo: Contest,
    neonStyle: Boolean = if (isPreview()) false else koinInject<SettingsManager>().state.value.neonStyles,
    shape: PartiallyCutCornerShape = PartiallyCutCornerShape(DpSize(12.dp, 31.dp)),
    navigator: Navigator = koinInject()
) {
    Spacer(modifier = Modifier.height(7.5.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .border(BorderStroke(1.dp, DarkPrimaryColor), shape = shape)
            .background(DarkPrimaryColorA12, shape)
            .drawWithContent {
                drawContent()
                if (neonStyle)
                    drawNeonStroke(DpSize(12.dp, 31.dp), DarkPrimaryColor)
            }
            .padding(horizontal = 15.dp)
            .clickable { navigator.navigateTo(Screen.HistoryDetailsScreen, contestInfo.id.toString()) }
    ) {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column() {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Calendar Icon",
                        tint = DarkPrimaryColor,
                        modifier = Modifier.width(16.dp)
                    )
                    Text(text = contestInfo.date, Modifier.padding(3.dp, 0.dp))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
                Row {
                    Text(
                        text = stringResource(id = contestInfo.difficultyLevel.stringRes),
                        color = contestInfo.difficultyLevel.color,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SportsScore,
                        contentDescription = "Score Icon",
                        tint = DarkPrimaryColor,
                        modifier = Modifier.width(18.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(text = contestInfo.points.toString())
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Time Icon",
                        tint = DarkPrimaryColor,
                        modifier = Modifier.width(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = contestInfo.duration.toString(includeSeconds = false)
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(7.5.dp))
}

@Preview
@Composable
fun HistoryItemPreview() {
    PreviewContainer {
        val contestInfo = Contest(
            id = 6,
            date = "18 april 2024",
            duration = Duration(1, 45, 0),
            points = 95,
            difficultyLevel = DifficultyLevel.LOW,
            rewardsCount = 15,
            enhancementsUsed = emptyList(),
            bypassedMonsters = 0,
            awardsEarned = listOf(
                Award.Grass,
                Award.Grass,
                Award.Flower
            ),
            isGameSuccess = true,
            gameLocation = GameLocation(
                latitude = 35.0,
                longitude = 30.0
            )
        )
        HistoryItem(contestInfo)
    }
}
