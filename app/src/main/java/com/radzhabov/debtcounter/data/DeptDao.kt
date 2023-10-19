package com.radzhabov.debtcounter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DeptDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDept(dept: Debt)

    @Delete
    suspend fun deleteDept(dept: Debt)

    @Query("SELECT * FROM debt WHERE id = :id")
    suspend fun getDeptById(id: Int): Debt?

    @Query("SELECT * FROM debt")
    fun getDebts(): Flow<List<Debt>>
}