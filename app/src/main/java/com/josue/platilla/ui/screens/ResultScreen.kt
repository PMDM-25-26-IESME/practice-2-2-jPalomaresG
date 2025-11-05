package com.josue.platilla.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.josue.platilla.R
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    navController: NavHostController,
    productName: String,
    price: String,
    vat: String,
    total: String,
    vatAmount: String
) {
    val context = LocalContext.current


    val displayProductName = if (productName == "NoProduct") "Not specified" else productName
    val displayPrice = try {
        NumberFormat.getCurrencyInstance().format(price.toDoubleOrNull() ?: 0.0)
    } catch (e: Exception) {
        "$$price"
    }
    val displayTotal = try {
        NumberFormat.getCurrencyInstance().format(total.toDoubleOrNull() ?: 0.0)
    } catch (e: Exception) {
        "$$total"
    }
    val displayVatAmount = try {
        NumberFormat.getCurrencyInstance().format(vatAmount.toDoubleOrNull() ?: 0.0)
    } catch (e: Exception) {
        "$$vatAmount"
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Product Total Price",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            shareCalculation(
                                context = context,
                                productName = displayProductName,
                                price = displayPrice,
                                vat = vat,
                                total = displayTotal,
                                vatAmount = displayVatAmount
                            )
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.share),
                            contentDescription = "Share calculation"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.padding_large))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ) {

            Text(
                text = "Calculation Summary",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_large))
            )


            ResultItem(
                label = "Product:",
                value = displayProductName
            )


            ResultItem(
                label = "Price (without VAT):",
                value = displayPrice
            )


            ResultItem(
                label = "VAT (%$vat):",
                value = displayVatAmount
            )


            ResultItem(
                label = "TOTAL:",
                value = displayTotal,
                isTotal = true
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))


            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(R.dimen.padding_large)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = "Back to Calculator",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun ResultItem(
    label: String,
    value: String,
    isTotal: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = if (isTotal) {
                MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold)
            } else {
                MaterialTheme.typography.bodyLarge
            },
            color = if (isTotal) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )
    }
}

private fun shareCalculation(
    context: Context,
    productName: String,
    price: String,
    vat: String,
    total: String,
    vatAmount: String
) {
    val shareText = """
        VAT Calculation Summary:
        Product: $productName
        Price: $price
        VAT (%$vat): $vatAmount
        TOTAL: $total
    """.trimIndent()

    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(intent, "Share calculation"))
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    com.josue.platilla.ui.theme.PlatillaTheme {
        // ResultScreen(
        //     navController = rememberNavController(),
        //     productName = "CPU",
        //     price = "300",
        //     vat = "21",
        //     total = "363.00",
        //     vatAmount = "63.00"
        // )
    }
}