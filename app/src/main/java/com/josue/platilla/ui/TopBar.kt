package com.josue.platilla.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.josue.platilla.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopBar(
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    showShareButton: Boolean = false,
    onShareClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "Back"
                    )
                }
            } else {
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(R.drawable.product),
                        contentDescription = stringResource(R.string.product_name)
                    )
                }
            }
        },
        actions = {
            if (showShareButton) {
                IconButton(onClick = onShareClick) {
                    Icon(
                        painter = painterResource(R.drawable.share),
                        contentDescription = "Share"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = modifier
    )
}