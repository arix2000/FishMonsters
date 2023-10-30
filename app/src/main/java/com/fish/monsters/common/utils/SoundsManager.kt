package com.fish.monsters.common.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import com.fish.monsters.R
import com.fish.monsters.common.extensions.fromPercents
import com.fish.monsters.features.settings.data.SettingsManager

class SoundsManager(private val context: Context, private val settingsManager: SettingsManager) {

    fun playDefaultButtonSound() {
        playSound(R.raw.default_button_click_sound)
    }

    private fun playSound(@RawRes resId: Int) {
        val mediaPlayer = MediaPlayer.create(context, resId).apply {
            val soundVolume = Float.fromPercents(settingsManager.state.value.soundPercentage)
            setVolume(soundVolume, soundVolume)
            start()
        }
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
    }

}