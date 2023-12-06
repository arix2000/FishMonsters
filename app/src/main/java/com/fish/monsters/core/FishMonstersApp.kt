package com.fish.monsters.core

import android.app.Application
import com.fish.monsters.common.utils.MusicManager
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.core.database.dao.ContestDao
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.core.di.appModule
import com.fish.monsters.core.di.databaseModule
import com.fish.monsters.core.di.viewModelModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class FishMonstersApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FishMonstersApp)
            modules(appModule, viewModelModule, databaseModule)
        }
        val settingsManager: SettingsManager = get()
        settingsManager.initSettingsFromDatabase().invokeOnCompletion {
            val musicManager: MusicManager = get()
            musicManager.setVolume(settingsManager.state.value.musicPercentage)
        }
        val contestDao: ContestDao = get()
        CoroutineScope(Dispatchers.IO).launch {
            if (contestDao.getAllContestInfo().isEmpty()) {
                contestDao.insertContestInfo(
                    Contest(
                        date = "08 November 2023",
                        duration = Duration(2, 30, 12),
                        points = 85,
                        difficultyLevel = DifficultyLevel.LOW,
                        rewardsCount = 10,
                        enhancementsUsed = listOf(
                            Enhancement(
                                name = "Enhancement 1",
                                time = Duration(1, 3, 18)
                            )
                        ),
                        bypassedMonsters = 0,
                        awardsEarned = listOf(
                            Award(
                                name = "Award 1"
                            )
                        ),
                        isGameSuccess = true,
                        gameLocation = GameLocation(
                            latitude = 10.0,
                            longitude = 10.0
                        )
                    ),
                )
                contestDao.insertContestInfo(
                    Contest(
                        date = "15 January 2024",
                        duration = Duration(1, 45, 30),
                        points = 92,
                        difficultyLevel = DifficultyLevel.MEDIUM,
                        rewardsCount = 8,
                        enhancementsUsed = listOf(
                            Enhancement(
                                name = "Enhancement 2",
                                time = Duration(0, 45, 0)
                            ),
                            Enhancement(
                                name = "Enhancement 3",
                                time = Duration(2, 0, 0)
                            )
                        ),
                        bypassedMonsters = 2,
                        awardsEarned = listOf(
                            Award(
                                name = "Award 2"
                            ),
                            Award(
                                name = "Award 3"
                            )
                        ),
                        isGameSuccess = false,
                        gameLocation = GameLocation(
                            latitude = 20.0,
                            longitude = 15.0
                        )
                    )
                )
                contestDao.insertContestInfo(
                    Contest(
                        date = "20 June 2025",
                        duration = Duration(0, 15, 45),
                        points = 78,
                        difficultyLevel = DifficultyLevel.HIGH,
                        rewardsCount = 15,
                        enhancementsUsed = emptyList(),
                        bypassedMonsters = 5,
                        awardsEarned = listOf(
                            Award(
                                name = "Award 4"
                            ),
                            Award(
                                name = "Award 5"
                            ),
                            Award(
                                name = "Award 6"
                            )
                        ),
                        isGameSuccess = true,
                        gameLocation = GameLocation(
                            latitude = 30.0,
                            longitude = 25.0
                        )
                    )
                )
            }
        }
    }
}