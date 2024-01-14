package com.fish.monsters.features.about

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.R
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.Developers
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.features.about.ui.DeveloperItem

@Composable
fun AboutScreen() {
    ScreenBox(title = stringResource(id = R.string.about)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(19.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = stringResource(id = R.string.created_by), fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(17.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable.fteam_logo),
                    contentDescription = "Team logo",
                    modifier = Modifier.height(199.dp)
                )
            }
            Spacer(modifier = Modifier.height(19.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(text = stringResource(id = R.string.meet_us), fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(19.dp))
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                for (developer in Developers.allDevelopers) {
                    DeveloperItem(developer)
                }
            }
        }
    }
}

@Preview
@Composable
private fun AboutScreenPreview() {
    FishMonstersTheme {
        Surface {
            AboutScreen()
        }
    }
}