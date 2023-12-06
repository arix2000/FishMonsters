package com.fish.monsters.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fish.monsters.core.database.entities.Contest

@Dao
interface ContestDao {
    @Insert
    suspend fun insertContestInfo(contestInfo: Contest)

    @Query("SELECT * FROM contest_info")
    suspend fun getAllContestInfo(): List<Contest>
}