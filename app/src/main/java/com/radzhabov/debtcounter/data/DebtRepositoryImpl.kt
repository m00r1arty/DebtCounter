package com.radzhabov.debtcounter.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DebtRepositoryImpl @Inject constructor(
    private val dao: DeptDao
) : DebtRepository  {
    override suspend fun insertDept(debt: Debt) {
        dao.insertDept(debt)
    }

    override suspend fun deleteDept(debt: Debt) {
        dao.deleteDept(debt)
    }

    override suspend fun getDeptById(id: Int): Debt? = dao.getDeptById(id)

    override fun getDebts(): Flow<List<Debt>> {
        return dao.getDebts()
    }
}