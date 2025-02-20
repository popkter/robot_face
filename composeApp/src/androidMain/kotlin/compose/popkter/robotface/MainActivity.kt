package compose.popkter.robotface

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // 获取屏幕的宽高信息
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp
            val screenHeight = configuration.screenHeightDp
            val isLandscape = screenWidth > screenHeight

//            App(isLandscape)

            AppV2(isLandscape)

        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}