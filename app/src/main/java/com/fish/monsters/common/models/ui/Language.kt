package com.fish.monsters.common.models.ui

import androidx.annotation.StringRes
import androidx.compose.ui.text.intl.Locale
import com.fish.monsters.R

enum class Language(@StringRes val titleId: Int, val code: String) {
    POLISH(R.string.polish,"pl"),
    ENGLISH(R.string.english,"en")
}

fun getAppCurrentLanguage(): Language {
    return Language.values().first { it.code == Locale.current.language }
}