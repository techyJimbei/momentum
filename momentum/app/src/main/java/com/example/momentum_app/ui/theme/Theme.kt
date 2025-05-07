import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.momentum_app.ui.theme.AppColors
import com.example.momentum_app.ui.theme.AppTypography
import com.example.momentum_app.ui.theme.LocalAppColors
import com.example.momentum_app.ui.theme.LocalAppTypography
import com.example.momentum_app.ui.theme.extendedColor
import com.example.momentum_app.ui.theme.extendedTypography


@Composable
fun AppTheme(
    content: @Composable () -> Unit
){
    CompositionLocalProvider(
        LocalAppColors provides extendedColor,
        LocalAppTypography provides extendedTypography
    ) {
        MaterialTheme(
            content = content
        )
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current
    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}