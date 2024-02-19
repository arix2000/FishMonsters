package com.fish.monsters.common.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import com.fish.monsters.R
import com.fish.monsters.common.extensions.fromPercents

class MusicManager(private val mediaPlayer: MediaPlayer, private val context: Context) {
    @RawRes
    private var currentMedia: Int = R.raw.app_default_music

    fun start(@RawRes resId: Int = currentMedia) {
        if (currentMedia != resId) {
            mediaPlayer.pause()
            mediaPlayer.reset()
        } else if (mediaPlayer.isPlaying) {
            return
        } else if (isPaused()) {
            mediaPlayer.start()
            return
        }

        currentMedia = resId
        val appMusicFile = context.resources.openRawResourceFd(resId)
        mediaPlayer.run {
            setDataSource(appMusicFile)
            isLooping = true
            prepare()
            start()
        }
    }

    private fun isPaused() = !mediaPlayer.isPlaying && mediaPlayer.currentPosition > 1

    fun resetToDefault() {
        currentMedia = R.raw.app_default_music
        start()
    }

    fun setVolume(percents: Int) {
        val floatPercents = Float.fromPercents(percents)
        mediaPlayer.setVolume(floatPercents, floatPercents)
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun stop() {
        mediaPlayer.reset()
    }
}
