package com.radzhabov.debtcounter.data

import kotlinx.coroutines.flow.Flow

interface DebtRepository {

    suspend fun insertDept(debt: Debt)
    suspend fun deleteDept(debt: Debt)
    suspend fun getDeptById(id: Int): Debt?
    fun getDebts(): Flow<List<Debt>>

}