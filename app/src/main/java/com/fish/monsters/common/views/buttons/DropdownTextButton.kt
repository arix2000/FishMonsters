package com.fish.monsters.common.views.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.FishMonstersTheme

@Composable
fun DropdownTextButton(
    items: List<String>,
    onItemClicked: (String) -> Unit,
    defaultButtonText: String = items.first()
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var buttonText by remember {
        mutableStateOf(defaultButtonText)
    }
    Row(modifier = Modifier.clickable {
        expanded = true
    }) {
        Text(text = buttonText, fontSize = 18.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Dropdown",
            tint = DarkPrimaryColor
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            items.forEach {
                DropdownMenuItem(
                    modifier = Modifier.defaultMinSize(minWidth = 130.dp),
                    text = { Text(text = it) },
                    onClick = {
                        buttonText = it
                        expanded = false
                        onItemClicked(it)
                    })
            }
        }
    }
}

@Preview
@Composable
private fun DropdownTextButtonPreview() {
    FishMonstersTheme {
        Surface {
            DropdownTextButton(listOf("Polski", "English"), onItemClicked = {})
        }
    }
}