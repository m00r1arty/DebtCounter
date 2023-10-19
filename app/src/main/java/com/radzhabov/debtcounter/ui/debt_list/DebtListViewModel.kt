package com.radzhabov.debtcounter.ui.debt_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.debtcounter.data.Debt
import com.radzhabov.debtcounter.data.DebtRepository
import com.radzhabov.debtcounter.domain.util.Routes
import com.radzhabov.debtcounter.domain.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DebtListViewModel @Inject constructor(
    private val repository: DebtRepository
): ViewModel() {
    val debt = repository.getDebts()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedDebt: Debt? = null

    fun onEvent(event: DebtListEvent) {
        when(event) {
            is DebtListEvent.OnDebtClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_DEBT + "?debtId=${event.debt.id}"))
            }
            is DebtListEvent.OnAddDebtClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_DEBT))
            }
            is DebtListEvent.OnUndoDeleteClick -> {
                deletedDebt?.let { debt ->
                    viewModelScope.launch {
                        repository.insertDept(debt)
                    }
                }
            }
            is DebtListEvent.OnDeleteDebtClick -> {
                viewModelScope.launch {
                    deletedDebt = event.debt
                    repository.deleteDept(event.debt)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Debt deleted",
                        action = "Undo"
                    ))
                }
            }
            is DebtListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertDept(
                        event.debt.copy(
                            isDone = event.inDone
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}