package com.josue.platilla.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.josue.platilla.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductTopBar() {
    TopAppBar(
        title = { Text(stringResource(R.string.app_title)) },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(painter = painterResource(R.drawable.product), contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}
