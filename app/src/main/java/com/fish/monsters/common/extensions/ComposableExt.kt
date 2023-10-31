package com.fish.monsters.common.extensions

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun isPreview() = LocalInspectionMode.current

@SuppressLint("UnrememberedMutableState")
@Composable
fun animateAlignmentAsState(
    targetAlignment: Alignment,
): State<Alignment> {
    val biased = targetAlignment as BiasAlignment
    val horizontal by animateFloatAsState(biased.horizontalBias, label = "animateFloatAsState")
    val vertical by animateFloatAsState(biased.verticalBias, label = "animateFloatAsState")
    return derivedStateOf { BiasAlignment(horizontal, vertical) }
}