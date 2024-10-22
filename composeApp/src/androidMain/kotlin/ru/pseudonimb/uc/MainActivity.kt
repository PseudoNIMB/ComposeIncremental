package ru.pseudonimb.uc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.withDI
import ru.pseudonimb.uc.screens.main.MainScreen
import ru.pseudonimb.uc.screens.main.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.Transparent.value.toInt(), Color.Transparent.value.toInt()
            ),
            navigationBarStyle = SystemBarStyle.auto(
                Color.Transparent.value.toInt(), Color.Transparent.value.toInt()
            )
        )
        super.onCreate(savedInstanceState)

        setContent {
            AppAndroidPreview()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() = withDI(injectDeps){
    MainScreen()
}