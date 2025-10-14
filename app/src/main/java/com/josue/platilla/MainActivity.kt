package com.josue.platilla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.josue.platilla.ui.ProductLayout
import com.josue.platilla.ui.theme.PlatillaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            PlatillaTheme {
                ProductLayout()
            }
        }
    }
}



