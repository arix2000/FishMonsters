package com.fish.monsters.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fish.monsters.core.database.entities.Contest

@Dao
interface ContestDao {
    @Insert
    suspend fun insertContest(contestInfo: Contest)

    @Query("SELECT * FROM contest_info")
    suspend fun getAllContests(): List<Contest>

    @Query("SELECT * FROM contest_info WHERE id = :contestId")
    suspend fun getContestDetailsById(contestId: Long): Contest
}