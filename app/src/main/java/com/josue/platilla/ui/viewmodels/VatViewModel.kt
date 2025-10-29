package com.josue.platilla.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat

class VatViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(VatState())
    val uiState: StateFlow<VatState> = _uiState.asStateFlow()

    fun onProductNameChanged(name: String) {
        _uiState.value = _uiState.value.copy(productName = name)
    }

    fun onPriceChanged(price: String) {
        _uiState.value = _uiState.value.copy(priceInput = price)
        calculateTotal()
    }

    fun onVatChanged(vat: String) {
        _uiState.value = _uiState.value.copy(vatInput = vat)
        calculateTotal()
    }

    private fun calculateTotal() {
        viewModelScope.launch {
            val price = _uiState.value.priceInput.toDoubleOrNull() ?: 0.0
            val vat = _uiState.value.vatInput.toDoubleOrNull() ?: 0.0
            val total = price * (1 + vat / 100)
            val formattedTotal = NumberFormat.getCurrencyInstance().format(total)

            _uiState.value = _uiState.value.copy(
                totalFormatted = formattedTotal
            )
        }
    }
}