package com.happymeerkat.avocado.presentation.navigation

import android.util.Log

const val title = "title"
const val description = "description"
const val categoryId = "categoryId"
const val dateMade = "dateMade"
const val dateDue = "dateDue"
const val timeDue = "timeDue"
const val completed = "completed"

sealed class Graph(val route: String) {
    object ROOT: Graph(route = "root_graph")
    object HOME: Graph(route = "home_graph")
    object DETAILS: Graph(
        route = "details_graph?title={$title}&description={$description}&categoryId={$categoryId}&dateMade={$dateMade}&dateDue={$dateDue}&timeDue={$timeDue}&completed={$completed}"
    ) {
        fun passDetails(
            title: String = "default title",
            description: String?,
            categoryId: Int = 1,
            dateMade: Long = 1,
            dateDue: Long?,
            timeDue: Long?,
            completed: Boolean? = false
        ): String{
            val out = "details_graph?title=$title&description=$description&categoryId=$categoryId&dateMade=$dateMade&dateDue=$dateDue&timeDue=$timeDue&completed=$completed"
            Log.d("PASSED DETAILS", out)
            return out
        }
    }

}