package com.radzhabov.debtcounter.ui.debt_list

import com.radzhabov.debtcounter.data.Debt

sealed class DebtListEvent {
    data class OnDeleteDebtClick(val debt: Debt): DebtListEvent()
    data class OnDoneChange(val debt: Debt, val inDone: Boolean): DebtListEvent()
    object OnUndoDeleteClick: DebtListEvent()
    data class OnDebtClick(val debt: Debt): DebtListEvent()
    object OnAddDebtClick: DebtListEvent()

}