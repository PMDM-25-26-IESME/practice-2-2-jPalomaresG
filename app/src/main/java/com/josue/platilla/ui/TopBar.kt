package com.josue.platilla.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.josue.platilla.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = androidx.compose.material3.MaterialTheme.typography.displayLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* TODO: Add navigation */ }) {
                Icon(
                    painter = painterResource(R.drawable.product),
                    contentDescription = stringResource(R.string.product_name),
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}