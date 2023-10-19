package com.radzhabov.debtcounter.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Debt::class],
    version = 1,
    exportSchema = false,
)
abstract class DebtDatabase: RoomDatabase() {
    abstract val dao: DeptDao

}