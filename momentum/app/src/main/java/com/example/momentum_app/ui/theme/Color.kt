package com.example.momentum_app.ui.theme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


val Blue = Color(0xFFA0CAFD)
val Sky_Blue = Color(0xFF84A59D)
val Navy_Blue = Color(0xFF184974)
val Powder_Blue = Color (0xFFD1E4FF)
val Grey = Color(0xFFD8DAE0)
val Dark_Grey = Color(0xFF2E3135)
val Dark = Color(0xFF003257)
val Logo = Color(0xFF1A78D6)

@Immutable
data class AppColors(
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val secondarySurface: Color,
    val onSecondarySurface: Color,
    val regularSurface: Color,
    val onRegularSurface: Color,
    val actionSurface: Color,
    val onActionSurface: Color,
    val highlightSurface: Color,
    val onHighlightSurface: Color,
    val onLogo: Color
)

val LocalAppColors = staticCompositionLocalOf {
    AppColors(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        surface = Color.Unspecified,
        onSurface = Color.Unspecified,
        secondarySurface = Color.Unspecified,
        onSecondarySurface = Color.Unspecified,
        regularSurface = Color.Unspecified,
        onRegularSurface = Color.Unspecified,
        actionSurface = Color.Unspecified,
        onActionSurface = Color.Unspecified,
        highlightSurface = Color.Unspecified,
        onHighlightSurface = Color.Unspecified,
        onLogo = Color.Unspecified
    )
}

val extendedColor = AppColors(
    background = Color.White,
    onBackground = Dark,
    surface = Color.White,
    onSurface = Dark,
    secondarySurface = Sky_Blue,
    onSecondarySurface = Color.White,
    regularSurface = Blue,
    onRegularSurface = Navy_Blue,
    actionSurface = Navy_Blue,
    onActionSurface = Powder_Blue,
    highlightSurface = Grey,
    onHighlightSurface = Dark_Grey,
    onLogo = Logo
)

