package com.josue.platilla.ui.viewmodels

data class VatState(
    val productName: String = "",
    val priceInput: String = "",
    val vatInput: String = "",
    val totalFormatted: String = "0.00"
)