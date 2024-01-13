package com.fish.monsters.common.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.core.theme.TextColorDark
import com.fish.monsters.core.theme.Typography

@Composable
fun SafetyInformationCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = TextColorDark,
    content: @Composable () -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = backgroundColor,
        shape = PartiallyCutCornerShape(DpSize(25.dp, 50.dp))
    ) {
        content()
    }
}

@Preview
@Composable
private fun SafetyInformationCardPreview() {
    SafetyInformationCard {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Uwaga! Upewnij się, że znajdujesz się w bezpiecznym miejscu.",
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}