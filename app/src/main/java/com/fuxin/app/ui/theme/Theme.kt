package com.fuxin.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val FuxinColorScheme = darkColorScheme(
    primary = Coral,
    onPrimary = WarmWhite,
    primaryContainer = SurfaceRaised,
    onPrimaryContainer = WarmWhite,
    secondary = Orange,
    onSecondary = Night,
    secondaryContainer = Color(0xFF54251F),
    onSecondaryContainer = WarmWhite,
    tertiary = ElectricPurple,
    onTertiary = WarmWhite,
    tertiaryContainer = Color(0xFF30235C),
    onTertiaryContainer = WarmWhite,
    background = Night,
    onBackground = WarmWhite,
    surface = SurfaceNight,
    onSurface = WarmWhite,
    surfaceVariant = SurfaceRaised,
    onSurfaceVariant = TextMuted,
    outline = DividerNight,
    outlineVariant = DividerNight
)

@Composable
fun 抚心Theme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FuxinColorScheme,
        typography = Typography,
        content = content
    )
}
