package com.josue.platilla.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josue.platilla.R
import java.text.NumberFormat

@Composable
fun ProductLayout() {
    var productName by remember { mutableStateOf("") }
    var priceInput by remember { mutableStateOf("") }
    var vatInput by remember { mutableStateOf("") }

    val price = priceInput.toDoubleOrNull() ?: 0.0
    val vat = vatInput.toDoubleOrNull() ?: 0.0
    val total = price + (price * vat / 100)
    val formattedTotal = NumberFormat.getCurrencyInstance().format(total)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Product Information",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(bottom = 16.dp)
        )

        EditField(
            label = "Product name",
            value = productName,
            onValueChanged = { productName = it },
            iconRes = R.drawable.product
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditField(
            label = "Price",
            value = priceInput,
            onValueChanged = { priceInput = it },
            iconRes = R.drawable.money,
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditField(
            label = "VAT (%)",
            value = vatInput,
            onValueChanged = { vatInput = it },
            iconRes = R.drawable.percent,
            keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Total price: $formattedTotal",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductLayoutPreview() {
    ProductLayout()
}
