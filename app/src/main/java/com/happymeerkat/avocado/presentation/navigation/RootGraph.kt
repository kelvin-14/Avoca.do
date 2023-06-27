package com.happymeerkat.avocado.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.happymeerkat.avocado.presentation.screens.details.DetailsView
import com.happymeerkat.avocado.presentation.screens.home.Home

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootGraph(
    modifier: Modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
    navController: NavHostController,
    askNotificationsPermission: () -> Unit
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT.route,
        startDestination = Graph.HOME.route,
    ) {
        composable(
            route = Graph.HOME.route
        ){
            Home(
                askNotificationsPermission = askNotificationsPermission,
                navigateToDetails = { id: Int -> navController.navigate(Graph.DETAILS.passDetails(id)) }
            )
        }
        composable(
            route = Graph.DETAILS.route,
            arguments = listOf(
                navArgument(ARG1) {
                    type = NavType.IntType
                }
            )
        ){
            DetailsView(
                backToHome = {navController.navigateUp()}
            )
        }
    }
}