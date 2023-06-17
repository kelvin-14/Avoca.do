package com.happymeerkat.avocado.presentation.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.happymeerkat.avocado.presentation.screens.DetailsView
import com.happymeerkat.avocado.presentation.screens.Home
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
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
                        title:String, description: String,categoryId: Int, dateMade: Long, dateDue: Long, timeDue: Long, completed: Boolean
                    -> navController.navigate(Graph.DETAILS.passDetails(title, description, categoryId, dateMade, dateDue, timeDue, completed))
                }
            )
        }
        composable(
            route = Graph.DETAILS.route,
            arguments = listOf(
                navArgument(title) {
                    type = NavType.StringType
                },
                navArgument(description) {
                    type = NavType.StringType
                },
                navArgument(categoryId) {
                    type = NavType.IntType
                },
                navArgument(dateMade) {
                    type = NavType.LongType
                },
                navArgument(dateDue) {
                    type = NavType.LongType
                },
                navArgument(timeDue) {
                    type = NavType.LongType
                },
                navArgument( completed ) {
                    type = NavType.BoolType
                },
            )
        ){
            it.arguments?.toString()?.let { it1 -> Log.d("NAV ARGUMENTS RECEIVED", it1) }
            DetailsView(
                title = it.arguments?.getString(title) ?: "no title",
                description = it.arguments?.getString(description),
                categoryId = it.arguments?.getInt(categoryId) ?: 0,
                dateMade = it.arguments?.getLong(dateMade) ?: 0,
                dateDue = it.arguments?.getLong(dateDue) ?: 0,
                timeDue = it.arguments?.getLong(timeDue) ?:0,
                completed = it.arguments?.getBoolean(completed) ?: false,
                backToHome = {navController.navigateUp()}
            )
        }
    }
}