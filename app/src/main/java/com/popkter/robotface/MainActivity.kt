package com.popkter.robotface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.popkter.robotface.tool.dp
import com.popkter.robotface.tool.px
import com.popkter.robotface.ui.view.SingleEye
import kotlin.math.max


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //hide system ui
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.hide(WindowInsetsCompat.Type.statusBars())
        insetsController.hide(WindowInsetsCompat.Type.navigationBars())

        setContent {

            var leftTopSliderIndex by remember { mutableFloatStateOf(300F) }
            var leftBottomSliderIndex by remember { mutableFloatStateOf(300F) }

            var rightTopSliderIndex by remember { mutableFloatStateOf(300F) }
            var rightBottomSliderIndex by remember { mutableFloatStateOf(300F) }

            var leftTopRadius by remember { mutableFloatStateOf(0F) }
            var leftBottomRadius by remember { mutableFloatStateOf(0F) }
            var rightTopRadius by remember { mutableFloatStateOf(0F) }
            var rightBottomRadius by remember { mutableFloatStateOf(0F) }


            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        modifier = Modifier
                            .weight(1F)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = "当前高度：$leftTopSliderIndex")
                        Slider(
                            value = leftTopSliderIndex,
                            onValueChange = {
                                leftTopSliderIndex = it
                            },
                            valueRange = 0F..300F,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )

                        Text(text = "上方角度：$leftTopRadius")
                        Slider(
                            value = leftTopRadius,
                            onValueChange = {
                                leftTopRadius = it
                            },
                            valueRange = 0F..1F,
                            steps = 9,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                    }


                    Column(
                        modifier = Modifier
                            .weight(1F)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        Text(text = "当前高度：$rightTopSliderIndex")
                        Slider(
                            value = rightTopSliderIndex,
                            onValueChange = {
                                rightTopSliderIndex = it
                            },
                            valueRange = 0F..300F,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )

                        Text(text = "上方角度：$rightTopRadius")
                        Slider(
                            value = rightTopRadius,
                            onValueChange = {
                                rightTopRadius = it
                            },
                            valueRange = 0F..1F,
                            steps = 9,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 100.dp)
                        .align(Alignment.BottomStart),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Column(
                        modifier = Modifier
                            .weight(1F)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = "当前高度：$leftBottomSliderIndex")
                        Slider(
                            value = leftBottomSliderIndex,
                            onValueChange = {
                                leftBottomSliderIndex = it
                            },
                            valueRange = 0F..300F,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )

                        Text(text = "下方角度：$leftBottomRadius")
                        Slider(
                            value = leftBottomRadius,
                            onValueChange = {
                                leftBottomRadius = it
                            },
                            valueRange = 0F..1F,
                            steps = 9,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1F)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(text = "当前高度：$rightBottomSliderIndex")
                        Slider(
                            value = rightBottomSliderIndex,
                            onValueChange = {
                                rightBottomSliderIndex = it
                            },
                            valueRange = 0F..300F,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )

                        Text(text = "下方角度：$rightBottomRadius")
                        Slider(
                            value = rightBottomRadius,
                            onValueChange = {
                                rightBottomRadius = it
                            },
                            valueRange = 0F..1F,
                            steps = 9,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                    }
                }

                Row(
                    Modifier
                        .size(600.px.dp, 600.px.dp)
                        .clip(RoundedCornerShape(350.px.dp))
                        .background(Color.Black)
                        .align(Alignment.Center),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Box(
                        Modifier
                            .width(300.px.dp)
                            .fillMaxHeight()

                    ) {
                        SingleEye(
                            eyesWidth = 300F,
                            upperEyelidHeight = leftTopSliderIndex,
                            lowerEyelidHeight = leftBottomSliderIndex,
                            th = max(0F, leftTopSliderIndex / 3 - leftTopSliderIndex / 3 * (1 - leftTopRadius)),
                            tlw = 50 - 50 * (1 - leftTopRadius),
                            trw = 50 - 50 * (1 - leftTopRadius),
                            bh = max(0F, leftBottomSliderIndex / 3 - leftBottomSliderIndex / 3 * (1 - leftBottomRadius)),
                            blw = 50 - 50 * (1 - leftBottomRadius),
                            btw = 50 - 50 * (1 - leftBottomRadius),
                            center = Offset(200F, 250F)
                        )
                    }

                    Box(
                        Modifier
                            .width(300.px.dp)
                            .fillMaxHeight()

                    ) {

                        SingleEye(
                            eyesWidth = 300F,
                            upperEyelidHeight = rightTopSliderIndex,
                            lowerEyelidHeight = rightBottomSliderIndex,
                            th = max(0F, rightTopSliderIndex / 3 - rightTopSliderIndex / 3 * (1 - rightTopRadius)),
                            tlw = 50 - 50 * (1 - rightTopRadius),
                            trw = 50 - 50 * (1 - rightTopRadius),
                            bh = max(0F, rightBottomSliderIndex / 3 - rightBottomSliderIndex / 3 * (1 - rightBottomRadius)),
                            blw = 50 - 50 * (1 - rightBottomRadius),
                            btw = 50 - 50 * (1 - rightBottomRadius),
                            center = Offset(100F, 250F)
                        )
                    }
                }

            }
        }
        /*        setContent {

                    var eyeState: EyeState by remember { mutableStateOf(EyeState.Ordinary) }


                    // 获取屏幕的宽高信息
                    val configuration = LocalConfiguration.current
                    val screenWidth = configuration.screenWidthDp
                    val screenHeight = configuration.screenHeightDp
                    val isLandscape = screenWidth > screenHeight

                    LaunchedEffect(Unit) {
                        snapshotFlow { eyeState }
                            .collect { state ->
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

                    RobotFaceTheme {

                        if (isLandscape) {
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

                                LazyVerticalGrid(
                                    modifier = Modifier.weight(1F),
                                    columns = GridCells.Fixed(2)
                                ) {
                                    items(items = EyeState.allStates, key = { it.name }) { state ->
                                        Log.d("EyeState", "Rendering: ${state.name}")  // 检查是否为 null
                                        Button(onClick = {
                                            eyeState = state
                                        }) {
                                            Text(text = state.name)
                                        }
                                    }
                                }
                            }
                        } else {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1F)
                                        .fillMaxHeight(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    RobotFace(eyeState)
                                }

                                LazyVerticalGrid(
                                    modifier = Modifier.weight(1F),
                                    columns = GridCells.Fixed(2)
                                ) {
                                    items(items = EyeState.allStates, key = { it.name }) { state ->
                                        Log.d("EyeState", "Rendering: ${state.name}")  // 检查是否为 null
                                        Button(onClick = {
                                            eyeState = state
                                        }) {
                                            Text(text = state.name)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }*/
    }
}
