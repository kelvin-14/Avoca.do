package com.happymeerkat.avocado.presentation.navigation

import android.util.Log

const val ARG1 = "id"

sealed class Graph(val route: String) {
    object ROOT: Graph(route = "root_graph")
    object HOME: Graph(route = "home_graph")
    object DETAILS: Graph(
        route = "details_graph/{$ARG1}"
    ) {
        fun passDetails(
            id: Int
        ): String{
            val out = this.route.replace(oldValue = "{$ARG1}", newValue = id.toString() )
            Log.d("PASSED DETAILS", out)
            return out
        }
    }

}