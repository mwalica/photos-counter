package ch.walica.photo_counter_1.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = BlueGrey20,
    onPrimary = BlueGrey90,
    primaryContainer = Blue40,
    onPrimaryContainer = Blue90,

    tertiary = Orange50,
    onTertiary = Orange90,

    background = BlueGrey10,
    onBackground = BlueGrey90,

    surface = BlueGrey20,
    onSurface = BlueGrey80,

    onSurfaceVariant = Blue95,

    outlineVariant = BlueGrey20,
)

private val LightColorScheme = lightColorScheme(
    primary = Blue40,
    onPrimary = Blue90,
    primaryContainer = BlueGrey50,
    onPrimaryContainer = BlueGrey90,


//    inversePrimary = Color(0xFF74F5DB),
//    secondary = Color(0xFFC82CE2),
//    onSecondary = Color(0xFFDFA3E9),
//    secondaryContainer = Color(0xFFDFA3E9),
//    onSecondaryContainer = Color(0xFFC82CE2),
    tertiary = Orange50,
    onTertiary = Orange90,
//    tertiaryContainer = Color(0xFFDCF0AB),
//    onTertiaryContainer = Color(0xFF3A3C2A),
    background = Color.White,
    onBackground = Blue20,
    surface = Blue40,
    onSurface = Blue80,
//    surfaceVariant = Color(0xFF46347E),
    onSurfaceVariant = Blue95,
//    surfaceTint = Color(0xFF262038),
//    inverseSurface = Color(0xFF7E5DB9),
//    inverseOnSurface = Color(0xFF9388C2),
//    error = Color(0xFFFF0000),
//    onError = Color(0xFFCE9AB8),
//    errorContainer = Color(0xFFB691A6),
//    onErrorContainer = Color(0xFF61093C),
//    outline = Color(0xFF074270),
    outlineVariant = BlueGrey90,
//    scrim = Color(0xFFCE9AB8)
)

@Composable
fun Photo_counter_1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography.copy(
            displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = fontFamily),
            displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = fontFamily),
            displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = fontFamily),
            headlineLarge = MaterialTheme.typography.headlineLarge.copy(fontFamily = fontFamily),
            headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = fontFamily),
            headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = fontFamily),
            titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = fontFamily),
            titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = fontFamily),
            titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = fontFamily),
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = fontFamily),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = fontFamily),
            bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = fontFamily),
            labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = fontFamily),
            labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = fontFamily),
            labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = fontFamily),
        ),
        content = content
    )
}