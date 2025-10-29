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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.josue.platilla.R
import java.text.NumberFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductLayout() {
    var productName by rememberSaveable { mutableStateOf("") }
    var priceInput by rememberSaveable { mutableStateOf("") }
    var vatInput by rememberSaveable { mutableStateOf("") }

    val price = priceInput.toDoubleOrNull() ?: 0.0
    val vat = vatInput.toDoubleOrNull() ?: 0.0
    val total = price + (price * vat / 100)
    val formattedTotal = NumberFormat.getCurrencyInstance().format(total)

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        topBar = { ProductTopBar() }
    ) { innerPadding ->
        if (isLandscape) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = dimensionResource(R.dimen.padding_large))
                    .statusBarsPadding()
                    .safeDrawingPadding(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = dimensionResource(R.dimen.padding_medium))
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = stringResource(R.string.vat_calculator_title),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
                    )

                    EditField(
                        label = stringResource(R.string.product_name),
                        value = productName,
                        onValueChanged = { productName = it },
                        iconRes = R.drawable.product
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                    EditField(
                        label = stringResource(R.string.price),
                        value = priceInput,
                        onValueChanged = { priceInput = it },
                        iconRes = R.drawable.money,
                        keyboardType = KeyboardType.Number
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

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
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .statusBarsPadding()
                    .safeDrawingPadding()
                    .padding(horizontal = dimensionResource(R.dimen.padding_large))
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.vat_calculator_title),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = dimensionResource(R.dimen.padding_medium))
                )

                EditField(
                    label = stringResource(R.string.product_name),
                    value = productName,
                    onValueChanged = { productName = it },
                    iconRes = R.drawable.product
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                EditField(
                    label = stringResource(R.string.price),
                    value = priceInput,
                    onValueChanged = { priceInput = it },
                    iconRes = R.drawable.money,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                EditField(
                    label = stringResource(R.string.vat),
                    value = vatInput,
                    onValueChanged = { vatInput = it },
                    iconRes = R.drawable.percent,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

                Text(
                    text = stringResource(R.string.total_price, formattedTotal),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductLayoutPreview() {
    com.josue.platilla.ui.theme.PlatillaTheme {
        ProductLayout()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProductLayoutDarkPreview() {
    com.josue.platilla.ui.theme.PlatillaTheme {
        ProductLayout()
    }
}