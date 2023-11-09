package com.fish.monsters.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fish.monsters.core.database.entities.ContestInfoEntity

@Dao
interface ContestDao {
    @Insert
    fun insertContestInfo(contestInfo: ContestInfoEntity)

    @Query("SELECT * FROM contest_info")
    fun getAllContestInfo(): List<ContestInfoEntity>
}