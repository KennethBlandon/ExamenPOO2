package ni.edu.uam.registroasistencia.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily

private val TimesNewRomanStyle = FontFamily.Serif

// Apply Times New Roman-like style globally to Material 3 typography.
val Typography = Typography().run {
    copy(
        displayLarge = displayLarge.copy(fontFamily = TimesNewRomanStyle),
        displayMedium = displayMedium.copy(fontFamily = TimesNewRomanStyle),
        displaySmall = displaySmall.copy(fontFamily = TimesNewRomanStyle),
        headlineLarge = headlineLarge.copy(fontFamily = TimesNewRomanStyle),
        headlineMedium = headlineMedium.copy(fontFamily = TimesNewRomanStyle),
        headlineSmall = headlineSmall.copy(fontFamily = TimesNewRomanStyle),
        titleLarge = titleLarge.copy(fontFamily = TimesNewRomanStyle),
        titleMedium = titleMedium.copy(fontFamily = TimesNewRomanStyle),
        titleSmall = titleSmall.copy(fontFamily = TimesNewRomanStyle),
        bodyLarge = bodyLarge.copy(fontFamily = TimesNewRomanStyle),
        bodyMedium = bodyMedium.copy(fontFamily = TimesNewRomanStyle),
        bodySmall = bodySmall.copy(fontFamily = TimesNewRomanStyle),
        labelLarge = labelLarge.copy(fontFamily = TimesNewRomanStyle),
        labelMedium = labelMedium.copy(fontFamily = TimesNewRomanStyle),
        labelSmall = labelSmall.copy(fontFamily = TimesNewRomanStyle)
    )
}
