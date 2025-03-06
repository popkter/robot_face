package compose.popkter.robotface

import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import compose.popkter.robotface.status.Blink
import compose.popkter.robotface.status.Coldness
import compose.popkter.robotface.status.Ordinary
import compose.popkter.robotface.status.RobotStatus
import compose.popkter.robotface.status.RobotStatus.Companion.canBlinkState
import compose.popkter.robotface.ui.drawAction
import compose.popkter.robotface.ui.drawEyes
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppV2(isLandScape: Boolean = false) {
    var eyeState: RobotStatus by remember { mutableStateOf(Coldness) }

    val textMeasurer = rememberTextMeasurer()

    val infiniteTransition = rememberInfiniteTransition()

    LaunchedEffect(Unit) {
        snapshotFlow { eyeState }
            .collect {
                while (eyeState.canBlinkState()) {
                    delay((200..3000).random().toLong())
                    if (!eyeState.canBlinkState()) break
                    eyeState = Blink
                    delay(220)
                    if (!eyeState.canBlinkState()) break
                    eyeState = Ordinary
                }
            }
    }

    val faceFillColor = Brush.sweepGradient(
        colors = listOf(Color.Blue, Color.Red, Color.White, Color.Blue),
        center = Offset(x = 20F, y = 20F)
    )

    if (isLandScape) {
        Row {
            Box(
                modifier = Modifier.weight(1F).fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .size(200.dp, 200.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(
                            brush = faceFillColor
                        )
                ) {

                    drawEyes(
                        modifier = Modifier.matchParentSize(),
                        status = eyeState,
                        infiniteTransition = infiniteTransition
                    )

                    drawAction(
                        modifier = Modifier.matchParentSize(),
                        status = eyeState,
                        textMeasurer = textMeasurer,
                        infiniteTransition = infiniteTransition
                    )
                }
            }

            Row(
                modifier = Modifier
                    .weight(1F)
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    items(items = RobotStatus.allStates, key = { it.toString() }) { state ->
                        if (eyeState == state) {
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
                                color = Color.Cyan.copy(alpha = 0.3F),
                            ) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text(text = state.toString())
                                }
                            }
                        } else {
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
                            ) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text(text = state.toString())
                                }
                            }
                        }
                    }
                }
            }

        }
    } else {

        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    Modifier
                        .size(200.dp, 200.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(
                            brush = faceFillColor
                        )
                ) {

                    drawEyes(
                        modifier = Modifier.matchParentSize(),
                        status = eyeState,
                        infiniteTransition = infiniteTransition
                    )


                    drawAction(
                        modifier = Modifier.matchParentSize(),
                        status = eyeState,
                        textMeasurer = textMeasurer,
                        infiniteTransition = infiniteTransition
                    )
                }
            }

            Row(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxWidth()
            ) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    items(items = RobotStatus.allStates, key = { it.toString() }) { state ->
                        if (eyeState == state) {
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
                                color = Color.Cyan.copy(alpha = 0.3F),
                            ) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text(text = state.toString())
                                }
                            }
                        } else {
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
                            ) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Text(text = state.toString())
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun AppV2Preview() {
    AppV2(true)
}

