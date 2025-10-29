package com.josue.platilla.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun EditField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    @DrawableRes iconRes: Int,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChanged,
        label = {
            Text(
                text = label,
                style = androidx.compose.material3.MaterialTheme.typography.labelSmall
            )
        },
        textStyle = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
        leadingIcon = {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = label,
                modifier = Modifier
                    .size(28.dp)
            )
        },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = androidx.compose.material3.MaterialTheme.shapes.medium
    )
}