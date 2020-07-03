package com.practice.calculator.ui

import androidx.compose.Composable
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.lightColorPalette

/**
 * Background colors
Primary: color displayed most frequently across your app’s screens and components.
Primary Variant: color is used to distinguish elements using primary colors, such as top app bar and the system bar.
Secondary: color provides more ways to accent and distinguish your product. Having a secondary color is optional, and should be applied sparingly to accent select parts of your UI.
Secondary Variant: color is used to distinguish elements using secondary colours.
Background: color appears behind scrollable content.
Surface: color uses on surfaces of components, like cards and menus.
Error: color used for indicating an error.
 * Typography and icon colors
On Primary color of text and icons displayed on top of the primary color.
On Secondary color of text and icons displayed on top of the secondary color.
On Background color of text and icons displayed on top of the background color.
On Surface color of text and icons displayed on top of the surface color.
On Error color of text and icons displayed on top of the error color.*/


val lightThemePallet = lightColorPalette(
    primary = Color(0xF6F8F9),
    secondary = Color(0xFB9405),

    onPrimary = Color(0x192220),
    onSecondary = Color.White
)

@Composable
fun LightTheme(content: @Composable() () -> Unit) {
    MaterialTheme(colors = lightThemePallet) {
        content()
    }
}