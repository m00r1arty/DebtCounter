package com.radzhabov.debtcounter.ui.add_edit_debt

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radzhabov.debtcounter.data.Debt
import com.radzhabov.debtcounter.data.DebtRepository
import com.radzhabov.debtcounter.domain.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditDebtViewModel @Inject constructor(
    private val repository: DebtRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var debt by mutableStateOf<Debt?>(null)
        private set

    var name by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var price by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val debtId = savedStateHandle.get<Int>("debtId") ?: -1
        if (debtId != -1) {
            viewModelScope.launch {
                repository.getDeptById(debtId)?.let { debt ->
                    name = debt.name
                    description = debt.description ?: ""
                    price = debt.price
                    this@AddEditDebtViewModel.debt = debt
                }
            }
        }
    }

    fun onEvent(event: AddEditDebtEvent) {
        when (event) {
            is AddEditDebtEvent.OnNameChange -> {
                name = event.title
            }
            is AddEditDebtEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditDebtEvent.OnPriceChange -> {
                price = event.price
            }
            is AddEditDebtEvent.OnSaveDebtClick -> {
                viewModelScope.launch {
                    if (name.isBlank() || price.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The title and price can't be empty"
                        ))
                    } else {
                        repository.insertDept(
                            Debt(
                                name = name,
                                description = description,
                                price = price,
                                isDone = debt?.isDone ?: false,
                                id = debt?.id
                            )
                        )
                        sendUiEvent(UiEvent.PopBackStack)
                    }
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
