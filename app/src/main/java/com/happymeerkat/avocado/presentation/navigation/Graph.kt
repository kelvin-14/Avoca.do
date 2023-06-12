package com.happymeerkat.avocado.presentation.navigation

const val ARG1 = "title"
const val ARG2 = "description"
sealed class Graph(val route: String) {
    object ROOT: Graph(route = "root_graph")
    object HOME: Graph(route = "home_graph")
    object DETAILS: Graph(route = "details_graph/{$ARG1}?description={$ARG2}") {
        fun passDetails(title: String = "", description: String = ""): String{
            return "details_graph/$title?description=$description"
        }
    }
}