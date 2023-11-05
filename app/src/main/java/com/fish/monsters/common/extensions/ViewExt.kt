package com.fish.monsters.common.extensions

import android.view.HapticFeedbackConstants
import android.view.View

fun View.performVibration(
    vibrationEnabled: Boolean,
    feedbackConstant: Int = HapticFeedbackConstants.KEYBOARD_TAP
) {
    if (vibrationEnabled) {
        this.performHapticFeedback(feedbackConstant)
    }
}