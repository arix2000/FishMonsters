package com.fish.monsters.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.widget.Toast
import com.fish.monsters.R

fun copyLocationToClipboard(context: Context, label: String, content: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(
        label,
        content
    )
    clipboardManager.setPrimaryClip(clip)
    if (Build.VERSION.SDK_INT < 33) {
        Toast.makeText(context, R.string.copied_to_clipboard, Toast.LENGTH_SHORT).show()
    }
}