package com.josue.platilla.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.josue.platilla.R
import com.josue.platilla.ui.viewmodels.VatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductLayout(
    vatViewModel: VatViewModel = viewModel()
) {
    val uiState = vatViewModel.uiState.value
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
                        value = uiState.productName,
                        onValueChanged = { vatViewModel.onProductNameChanged(it) },
                        iconRes = R.drawable.product
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                    EditField(
                        label = stringResource(R.string.price),
                        value = uiState.priceInput,
                        onValueChanged = { vatViewModel.onPriceChanged(it) },
                        iconRes = R.drawable.money,
                        keyboardType = KeyboardType.Number
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

                    EditField(
                        label = stringResource(R.string.vat),
                        value = uiState.vatInput,
                        onValueChanged = { vatViewModel.onVatChanged(it) },
                        iconRes = R.drawable.percent,
                        keyboardType = KeyboardType.Number
                    )
                }

                Text(
                    text = stringResource(R.string.total_price, uiState.totalFormatted),
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
                    value = uiState.productName,
                    onValueChanged = { vatViewModel.onProductNameChanged(it) },
                    iconRes = R.drawable.product
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                EditField(
                    label = stringResource(R.string.price),
                    value = uiState.priceInput,
                    onValueChanged = { vatViewModel.onPriceChanged(it) },
                    iconRes = R.drawable.money,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                EditField(
                    label = stringResource(R.string.vat),
                    value = uiState.vatInput,
                    onValueChanged = { vatViewModel.onVatChanged(it) },
                    iconRes = R.drawable.percent,
                    keyboardType = KeyboardType.Number
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

                Text(
                    text = stringResource(R.string.total_price, uiState.totalFormatted),
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