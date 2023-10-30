package com.fish.monsters.common.models.ui

import androidx.annotation.StringRes
import com.fish.monsters.R

enum class Language(@StringRes val titleId: Int, val code: String) {
    POLISH(R.string.polish,"pl"),
    ENGLISH(R.string.english,"en")
}