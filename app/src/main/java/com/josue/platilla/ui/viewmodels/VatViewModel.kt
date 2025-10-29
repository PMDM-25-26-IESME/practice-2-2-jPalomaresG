package com.josue.vatcalculator.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import java.text.NumberFormat

class VatViewModel : ViewModel() {

    private val _uiState = mutableStateOf(VatState())
    val uiState: State<VatState> = _uiState

    fun onProductNameChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(productName = newValue)
    }

    fun onPriceChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(priceInput = newValue)
        calculateTotal()
    }

    fun onVatChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(vatInput = newValue)
        calculateTotal()
    }

    private fun calculateTotal() {
        val price = _uiState.value.priceInput.toDoubleOrNull() ?: 0.0
        val vat = _uiState.value.vatInput.toDoubleOrNull() ?: 0.0
        val total = price + (price * vat / 100)
        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        _uiState.value = _uiState.value.copy(totalFormatted = formattedTotal)
    }
}
