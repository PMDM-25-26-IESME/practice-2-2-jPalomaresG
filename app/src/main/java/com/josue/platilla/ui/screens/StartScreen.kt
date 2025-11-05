package com.josue.platilla.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.josue.platilla.R
import com.josue.platilla.ui.EditField
import com.josue.platilla.ui.ProductTopBar
import com.josue.platilla.ui.viewmodels.VatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    navController: NavHostController,
    vatViewModel: VatViewModel
) {
    val uiState by vatViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            ProductTopBar(
                showBackButton = false,
                onBackClick = {},
                showShareButton = false,
                onShareClick = {}
            )
        },
        floatingActionButton = {
            Button(
                onClick = {
                    val price = uiState.priceInput.toDoubleOrNull() ?: 0.0
                    val vat = uiState.vatInput.toDoubleOrNull() ?: 0.0
                    val vatAmount = price * (vat / 100)
                    val formattedVatAmount = "%.2f".format(vatAmount)

                    val safeProductName = uiState.productName.ifBlank { "NoProduct" }.replace("/", "-")
                    val safePrice = uiState.priceInput.ifBlank { "0" }
                    val safeVat = uiState.vatInput.ifBlank { "0" }
                    val safeTotal = uiState.totalFormatted.ifBlank { "0.00" }.replace("$", "").replace(",", "")
                    val safeVatAmount = formattedVatAmount.replace("$", "").replace(",", "")

                    navController.navigate(
                        "result/$safeProductName/$safePrice/$safeVat/$safeTotal/$safeVatAmount"
                    )
                },
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_large))
                    .fillMaxWidth(),
                enabled = uiState.priceInput.isNotBlank() && uiState.vatInput.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = "View Total Price",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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


            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    com.josue.platilla.ui.theme.PlatillaTheme {
        // StartScreen(navController = rememberNavController(), vatViewModel = VatViewModel())
    }
}