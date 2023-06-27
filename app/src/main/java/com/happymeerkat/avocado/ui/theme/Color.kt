package com.happymeerkat.avocado.ui.theme

import androidx.compose.ui.graphics.Color



// Light
val LightGreen = Color(0x606c38)
val DarkGreen = Color(0x283618)
val LightSuede = Color(0xfefae0)
val LightBrown = Color(0xdda15e)

// Dark
val Black = Color(0x000814)
val DarkBlue = Color(0x14213d)
val Gold = Color(0xfca311)
val White = Color(0xffffff)




sealed class ThemeColors (
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color
) {
    object Dark : ThemeColors (
        background = Black,
        surface = DarkBlue,
        primary = Gold,
        text = White
    )
    object Light : ThemeColors (
        background = LightSuede,
        surface = LightBrown,
        primary = LightGreen,
        text = DarkGreen
    )
}
