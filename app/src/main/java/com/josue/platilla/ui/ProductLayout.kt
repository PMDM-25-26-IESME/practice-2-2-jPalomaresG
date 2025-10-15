package com.josue.platilla.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.josue.platilla.R
import java.text.NumberFormat

@Composable
fun ProductLayout() {

    var productName by rememberSaveable { mutableStateOf("") }
    var priceInput by rememberSaveable { mutableStateOf("") }
    var vatInput by rememberSaveable { mutableStateOf("") }

    val price = priceInput.toDoubleOrNull() ?: 0.0
    val vat = vatInput.toDoubleOrNull() ?: 0.0
    val total = price + (price * vat / 100)
    val formattedTotal = String.format("%.2f", total) // ✅ Redondeado a 2 decimales


    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .statusBarsPadding()
                .safeDrawingPadding(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.app_title),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                EditField(
                    label = stringResource(R.string.product_name),
                    value = productName,
                    onValueChanged = { productName = it },
                    iconRes = R.drawable.product
                )

                Spacer(modifier = Modifier.height(12.dp))

                EditField(
                    label = stringResource(R.string.price),
                    value = priceInput,
                    onValueChanged = { priceInput = it },
                    iconRes = R.drawable.money,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(12.dp))

                EditField(
                    label = stringResource(R.string.vat),
                    value = vatInput,
                    onValueChanged = { vatInput = it },
                    iconRes = R.drawable.percent,
                    keyboardType = KeyboardType.Number
                )
            }

            Text(
                text = stringResource(R.string.total_price, formattedTotal),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    } else {

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
                text = stringResource(R.string.app_title),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 16.dp)
            )

            EditField(
                label = stringResource(R.string.product_name),
                value = productName,
                onValueChanged = { productName = it },
                iconRes = R.drawable.product
            )

            Spacer(modifier = Modifier.height(16.dp))

            EditField(
                label = stringResource(R.string.price),
                value = priceInput,
                onValueChanged = { priceInput = it },
                iconRes = R.drawable.money,
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(16.dp))

            EditField(
                label = stringResource(R.string.vat),
                value = vatInput,
                onValueChanged = { vatInput = it },
                iconRes = R.drawable.percent,
                keyboardType = KeyboardType.Number
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.total_price, formattedTotal),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductLayoutPreview() {
    ProductLayout()
}
