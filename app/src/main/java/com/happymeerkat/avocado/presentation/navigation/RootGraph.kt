package com.happymeerkat.avocado.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.happymeerkat.avocado.presentation.screens.DetailsView
import com.happymeerkat.avocado.presentation.screens.Home

@Composable
fun RootGraph(
    navController: NavHostController
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
                navigateToDetails = {
                        title:String, description: String -> navController.navigate(Graph.DETAILS.passDetails(title, description))
                }
            )
        }
        composable(
            route = Graph.DETAILS.route,
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = "HAKUNA KITU"
                },
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = "HAKUNA KITU"
                }
            )
        ){
            DetailsView(
                title = it.arguments?.getString("title") ?: "no title",
                description = it.arguments?.getString("description") ?: "no description",
                backToHome = {navController.navigateUp()}
            )
        }

    }
}