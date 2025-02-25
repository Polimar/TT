package com.valcan.trendytracker.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val KawaiiLightColorScheme = lightColorScheme(
    primary = Colors.PastelPink,
    secondary = Colors.PastelBlue,
    tertiary = Colors.PastelPurple,
    background = Colors.PastelYellow,
    surface = Color.White
)

private val KawaiiDarkColorScheme = darkColorScheme(
    primary = Colors.PastelPink.copy(alpha = 0.8f),
    secondary = Colors.PastelBlue.copy(alpha = 0.8f),
    tertiary = Colors.PastelPurple.copy(alpha = 0.8f),
    background = Color.Black.copy(alpha = 0.9f),
    surface = Color.Black.copy(alpha = 0.8f)
)

@Composable
fun TrendyTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) KawaiiDarkColorScheme else KawaiiLightColorScheme
        }
        darkTheme -> KawaiiDarkColorScheme
        else -> KawaiiLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography.copy(
            headlineMedium = Typography.headlineMedium.copy(
                fontFamily = KawaiiFont
            ),
            titleMedium = Typography.titleMedium.copy(
                fontFamily = KawaiiFont
            ),
            bodyMedium = Typography.bodyMedium.copy(
                fontFamily = KawaiiFont
            )
        ),
        content = content
    )
}