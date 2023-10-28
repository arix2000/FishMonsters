package com.fish.monsters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fish.monsters.core.navigation.AppNavHost
import com.fish.monsters.core.theme.FishMonstersTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FishMonstersTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    KoinContext {
                        AppNavHost()
                    }
                }
            }
        }
    }
}