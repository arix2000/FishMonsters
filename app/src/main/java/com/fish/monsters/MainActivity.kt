package com.fish.monsters

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.fish.monsters.common.utils.MusicManager
import com.fish.monsters.core.navigation.AppNavHost
import com.fish.monsters.core.theme.FishMonstersTheme
import org.koin.android.ext.android.get
import org.koin.compose.KoinContext

class MainActivity : AppCompatActivity() {
    private val musicManager: MusicManager = get()

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

    override fun onResume() {
        super.onResume()
        musicManager.start()
    }

    override fun onPause() {
        musicManager.pause()
        super.onPause()
    }

    override fun onStop() {
        musicManager.stop()
        super.onStop()
    }
}