package com.josue.platilla

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.josue.platilla.ui.navigation.NavGraph
import com.josue.platilla.ui.theme.PlatillaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            PlatillaTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}