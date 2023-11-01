package com.fish.monsters.common.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.fish.monsters.common.utils.SoundsManager
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.core.database.dao.SettingsDao
import com.fish.monsters.core.database.entities.Settings
import kotlinx.coroutines.flow.Flow

@Composable
fun previewGetSoundsManager(): SoundsManager {
    return SoundsManager(LocalContext.current, SettingsManager(object : SettingsDao {
        override fun getSettings(): Flow<Settings> {
            throw Exception("Only for preview use")
        }

        override suspend fun insert(settings: Settings) {}
        override suspend fun update(settings: Settings) {}
    }))
}