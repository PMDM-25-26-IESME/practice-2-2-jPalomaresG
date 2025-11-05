package com.josue.platilla.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.josue.platilla.ui.screens.ResultScreen
import com.josue.platilla.ui.screens.StartScreen
import com.josue.platilla.ui.viewmodels.VatViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    val vatViewModel: VatViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable("start") {
            StartScreen(
                navController = navController,
                vatViewModel = vatViewModel
            )
        }
        composable("result/{productName}/{price}/{vat}/{total}/{vatAmount}") { backStackEntry ->
            val productName = backStackEntry.arguments?.getString("productName") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            val vat = backStackEntry.arguments?.getString("vat") ?: ""
            val total = backStackEntry.arguments?.getString("total") ?: ""
            val vatAmount = backStackEntry.arguments?.getString("vatAmount") ?: ""

            ResultScreen(
                navController = navController,
                productName = productName,
                price = price,
                vat = vat,
                total = total,
                vatAmount = vatAmount
            )
        }
    }
}