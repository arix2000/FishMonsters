package com.fish.monsters.common.views.screenContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.models.ui.IconProps
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.buttons.IconFishButton
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.core.theme.TextColorDark

@Composable
fun FishTopBar(
    title: String? = null,
    navigator: Navigator,
    onBackButtonClicked: ((Navigator) -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    val partiallyCutCornerShape = remember {
        PartiallyCutCornerShape(DpSize(11.dp, 22.dp))
    }
    Row(
        modifier = Modifier
            .height(60.dp)
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        IconFishButton(
            modifier = Modifier.fillMaxHeight(), onClick = {
                if (onBackButtonClicked != null)
                    onBackButtonClicked(navigator)
                else
                    navigator.popBackStack()
            }, iconProps = IconProps(Icons.Default.ArrowBack)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clip(partiallyCutCornerShape)
                .background(DarkPrimaryColor, partiallyCutCornerShape)
        ) {
            ProvideTextStyle(TextStyle(color = TextColorDark)) {
                if (title.isNullOrBlank()) content()
                else CapText(text = title)
            }
        }
    }
}

@Preview
@Composable
private fun AppTopBarPreview() {
    FishMonstersTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                FishTopBar("Wybierz poziom trudności", Navigator())
            }
        }
    }
}