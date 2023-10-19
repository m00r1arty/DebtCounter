package com.radzhabov.debtcounter.ui.add_edit_debt

sealed class AddEditDebtEvent {
    data class OnTitleChange(val title: String): AddEditDebtEvent()
    data class OnDescriptionChange(val description: String): AddEditDebtEvent()
    data class OnPriceChange(val price: String): AddEditDebtEvent()
    object OnSaveDebtClick: AddEditDebtEvent()
}
