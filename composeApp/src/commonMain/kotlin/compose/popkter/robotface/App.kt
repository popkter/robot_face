import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import compose.project.demo.EyeState
import compose.project.demo.EyeState.Companion.canBlinkState
import compose.project.demo.RobotFace
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    var eyeState: EyeState by remember { mutableStateOf(EyeState.Ordinary) }

    LaunchedEffect(Unit) {
        snapshotFlow { eyeState }
            .collect {
                while (eyeState.canBlinkState()) {
                    delay((100..3000).random().toLong())
                    if (!eyeState.canBlinkState()) break
                    eyeState = EyeState.Blink
                    delay(200)
                    if (!eyeState.canBlinkState()) break
                    eyeState = EyeState.Ordinary
                }
            }
    }


    MaterialTheme {

        if (isLandscape()) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    RobotFace(eyeState)
                }

                Box(modifier = Modifier.weight(1F).fillMaxHeight()) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(items = EyeState.allStates, key = { it.name }) { state ->
                            Surface(
                                modifier = Modifier.padding(10.dp).clip(RoundedCornerShape(5.dp))
                                    .background(Color.Blue.copy(.5F))
                                    .clickable { eyeState = state }
                            ) {
                                Text(text = state.name)
                            }
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    RobotFace(eyeState)
                }

                Box(
                    modifier = Modifier
                        .weight(1.5F)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(items = EyeState.allStates, key = { it.name }) { state ->
                            Surface(

                                modifier = Modifier
                                    .padding(5.dp)
                                    .height(40.dp)
                                    .border(
                                        width = 1.dp, brush = Brush.verticalGradient(
                                            colors = listOf(Color.Red, Color.White, Color.Gray)
                                        ), shape = RoundedCornerShape(5.dp)
                                    )
                                    .clip(RoundedCornerShape(5.dp))
                                    .clickable { eyeState = state },
                                color = Color.Gray.copy(alpha = 0.1F),
//                                contentColor = Color.White.copy(alpha = 0.5F)
                            ) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text(text = state.name)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getScreenSizeDp(): DpSize {
    val density = LocalDensity.current
    val windowSize = WindowInsets.systemBars // 获取系统窗口信息
    val layoutDirection = LocalLayoutDirection.current
    return DpSize(
        (windowSize.getLeft(density, layoutDirection) + windowSize.getRight(density, layoutDirection)).dp,
        (windowSize.getTop(density) + windowSize.getBottom(density)).dp
    )
}

@Composable
fun isLandscape(): Boolean {
    val screenSize = getScreenSizeDp()
    return screenSize.width > screenSize.height
}