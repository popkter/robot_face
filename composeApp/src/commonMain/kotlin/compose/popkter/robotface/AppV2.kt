package compose.popkter.robotface

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import compose.popkter.robotface.ext.RobotStatus
import compose.popkter.robotface.ext.RobotStatus.Companion.canBlinkState
import compose.popkter.robotface.ui.drawEye
import kotlinx.coroutines.delay

@Composable
fun AppV2(isLandScape: Boolean = false) {
    var eyeState: RobotStatus by remember { mutableStateOf(RobotStatus.Ordinary) }

    LaunchedEffect(Unit) {
        snapshotFlow { eyeState }
            .collect {
                while (eyeState.canBlinkState()) {
                    delay((200..3000).random().toLong())
                    if (!eyeState.canBlinkState()) break
                    eyeState = RobotStatus.Blink
                    delay(220)
                    if (!eyeState.canBlinkState()) break
                    eyeState = RobotStatus.Ordinary
                }
            }
    }


    val leftUpperEyelidRadius by animateFloatAsState(
        targetValue = eyeState.leftUpperEyelidRadius,
        animationSpec = tween(durationMillis = 240, easing = LinearEasing)
    )

    val leftLowerEyelidRadius by animateFloatAsState(
        targetValue = eyeState.leftLowerEyelidRadius,
        animationSpec = tween(durationMillis = 240, easing = LinearEasing)
    )

    val rightUpperEyelidRadius by animateFloatAsState(
        targetValue = eyeState.rightUpperEyelidRadius,
        animationSpec = tween(durationMillis = 240, easing = LinearEasing)
    )

    val rightLowerEyelidRadius by animateFloatAsState(
        targetValue = eyeState.rightLowerEyelidRadius,
        animationSpec = tween(durationMillis = 240, easing = LinearEasing)
    )

    val infiniteTransition = rememberInfiniteTransition()

    //Both Eyes Rotate
    val eyesRotatedAngle by if (eyeState.rotateInfinite) {
        infiniteTransition.animateFloat(
            initialValue = eyeState.startAngle,
            targetValue = eyeState.endAngle,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = eyeState.rotateDuration, easing = eyeState.rotateEasing),
                repeatMode = eyeState.rotateRepeatMode
            )
        )
    } else {
        animateFloatAsState(
            targetValue = eyeState.endAngle,
            animationSpec = tween(durationMillis = eyeState.rotateDuration, easing = eyeState.rotateEasing)
        )
    }

    //horizontal translation
    val bothEyesHorizontalTransition by if (eyeState.horizontalTranslationInfinite) {
        infiniteTransition.animateFloat(
            initialValue = eyeState.startHorizontalTranslation,
            targetValue = eyeState.endHorizontalTranslation,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = eyeState.horizontalTranslationDuration,
                    easing = eyeState.horizontalEasing
                ),
                repeatMode = eyeState.horizontalTranslationRepeatMode
            )
        )
    } else {
        animateFloatAsState(
            targetValue = eyeState.endHorizontalTranslation,
            animationSpec = tween(
                durationMillis = eyeState.horizontalTranslationDuration,
                easing = eyeState.horizontalEasing
            )
        )
    }

    //vertical translation
    val bothEyesVerticalTransition by if (eyeState.verticalTranslationInfinite) {
        infiniteTransition.animateFloat(
            initialValue = eyeState.startVerticalTranslation,
            targetValue = eyeState.endVerticalTranslation,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = eyeState.verticalTranslationDuration, easing = eyeState.verticalEasing),
                repeatMode = eyeState.verticalTranslationRepeatMode
            )
        )
    } else {
        animateFloatAsState(
            targetValue = eyeState.endVerticalTranslation,
            animationSpec = tween(durationMillis = eyeState.verticalTranslationDuration, easing = eyeState.verticalEasing)
        )
    }

    //scaleX
    val bothEyesScaleX by animateFloatAsState(
        targetValue = eyeState.scaleX,
        animationSpec = if (eyeState.scaleXInfinite) {
            infiniteRepeatable(
                animation = tween(durationMillis = eyeState.scaleXDuration, easing = eyeState.scaleXEasing),
                repeatMode = eyeState.scaleXRepeatMode
            )
        } else {
            tween(durationMillis = eyeState.scaleXDuration, easing = eyeState.scaleXEasing)
        }
    )

    //scaleY
    val bothEyesScaleY by animateFloatAsState(
        targetValue = eyeState.scaleY,
        animationSpec = if (eyeState.scaleYInfinite) {
            infiniteRepeatable(
                animation = tween(durationMillis = eyeState.scaleYDuration, easing = eyeState.scaleYEasing),
                repeatMode = eyeState.scaleYRepeatMode
            )
        } else {
            tween(durationMillis = eyeState.scaleYDuration, easing = eyeState.scaleYEasing)
        }
    )

    var leftEyeCornerRadius by remember { mutableFloatStateOf(30F) }
    var rightEyeCornerRadius by remember { mutableFloatStateOf(30F) }

    val leftEyeRotatedAngle by if (eyeState.leftEyeRotateInfinite) {
        infiniteTransition.animateFloat(
            initialValue = eyeState.startLeftEyeRotateAngle,
            targetValue = eyeState.endLeftEyeRotateAngle,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = eyeState.leftEyeRotateDuration,
                    easing = eyeState.leftEyeRotateEasing
                ),
                repeatMode = eyeState.leftEyeRotateRepeatMode
            )
        )
    } else {
        animateFloatAsState(
            targetValue = eyeState.endLeftEyeRotateAngle,
            animationSpec = tween(
                durationMillis = eyeState.leftEyeRotateDuration,
                easing = eyeState.leftEyeRotateEasing
            )
        )
    }

    val rightEyeRotatedAngle by if (eyeState.rightEyeRotateInfinite) {
        infiniteTransition.animateFloat(
            initialValue = eyeState.startRightEyeRotateAngle,
            targetValue = eyeState.endRightEyeRotateAngle,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = eyeState.rightEyeRotateDuration,
                    easing = eyeState.rightEyeRotateEasing
                ),
                repeatMode = eyeState.rightEyeRotateRepeatMode
            )
        )
    } else {
        animateFloatAsState(
            targetValue = eyeState.endRightEyeRotateAngle,
            animationSpec = tween(
                durationMillis = eyeState.rightEyeRotateDuration,
                easing = eyeState.rightEyeRotateEasing
            )
        )
    }


    val leftEyeHorizontalTransition by if (eyeState.leftEyeHorizontalTranslationInfinite) {
        infiniteTransition.animateFloat(
            initialValue = eyeState.startLeftEyeHorizontalTranslation,
            targetValue = eyeState.endLeftEyeHorizontalTranslation,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = eyeState.leftEyeHorizontalTranslationDuration,
                    easing = eyeState.leftEyeHorizontalEasing
                ),
                repeatMode = eyeState.leftEyeHorizontalTranslationRepeatMode
            )
        )
    } else {
        animateFloatAsState(
            targetValue = eyeState.endLeftEyeHorizontalTranslation,
            animationSpec = tween(
                durationMillis = eyeState.leftEyeHorizontalTranslationDuration,
                easing = eyeState.leftEyeHorizontalEasing
            )
        )
    }

    val rightEyeHorizontalTransition by if (eyeState.rightEyeHorizontalTranslationInfinite) {
        infiniteTransition.animateFloat(
            initialValue = eyeState.startRightEyeHorizontalTranslation,
            targetValue = eyeState.endRightEyeHorizontalTranslation,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = eyeState.rightEyeHorizontalTranslationDuration,
                    easing = eyeState.rightEyeHorizontalEasing
                ),
                repeatMode = eyeState.rightEyeVerticalTranslationRepeatMode
            )
        )
    } else {
        animateFloatAsState(
            targetValue = eyeState.endRightEyeHorizontalTranslation,
            animationSpec = tween(
                durationMillis = eyeState.rightEyeHorizontalTranslationDuration,
                easing = eyeState.rightEyeHorizontalEasing
            )
        )
    }

    val leftEyeVerticalTransition by if (eyeState.leftEyeVerticalTranslationInfinite) {
        infiniteTransition.animateFloat(
            initialValue = eyeState.startLeftEyeVerticalTranslation,
            targetValue = eyeState.endLeftEyeVerticalTranslation,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = eyeState.leftEyeVerticalTranslationDuration,
                    easing = eyeState.leftEyeVerticalEasing
                ),
                repeatMode = eyeState.leftEyeVerticalTranslationRepeatMode
            )
        )
    } else {
        animateFloatAsState(
            targetValue = eyeState.endLeftEyeVerticalTranslation,
            animationSpec = tween(
                durationMillis = eyeState.leftEyeVerticalTranslationDuration,
                easing = eyeState.leftEyeVerticalEasing
            )
        )
    }

    val rightEyeVerticalTransition by if (eyeState.rightEyeVerticalTranslationInfinite) {
        infiniteTransition.animateFloat(
            initialValue = eyeState.startRightEyeVerticalTranslation,
            targetValue = eyeState.endRightEyeVerticalTranslation,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = eyeState.rightEyeVerticalTranslationDuration,
                    easing = eyeState.rightEyeVerticalEasing
                ),
                repeatMode = eyeState.rightEyeVerticalTranslationRepeatMode
            )
        )
    } else {
        animateFloatAsState(
            targetValue = eyeState.endRightEyeVerticalTranslation,
            animationSpec = tween(
                durationMillis = eyeState.rightEyeVerticalTranslationDuration,
                easing = eyeState.rightEyeVerticalEasing
            )
        )
    }

    val density = LocalDensity.current

    val faceWidth = with(density) { 200.dp.toPx() }

    val leftEyeOffset = Offset(faceWidth / 3, faceWidth * 2 / 5)
    val rightEyeOffset = Offset(faceWidth * 2 / 3, faceWidth * 2 / 5)

    val eyesCenterPivot = Offset(faceWidth / 2, faceWidth * 2 / 5)
    val eyesCenterOffsetPivot = Offset(faceWidth / 2, faceWidth * 2 / 5 + 100)

    Box(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //left
            Column(
                modifier = Modifier.weight(1F).padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(text = "上眼皮高度：$leftUpperEyelidRadius")
                Slider(
                    value = leftUpperEyelidRadius,
                    onValueChange = {
//                        leftUpperEyelidHeight = it
                    },
                    valueRange = 0F..100F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "下眼皮高度：$leftLowerEyelidRadius")
                Slider(
                    value = leftLowerEyelidRadius,
                    onValueChange = {
//                        leftLowerEyelidHeight = it
                    },
                    valueRange = 0F..100F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "水平偏移：${leftEyeHorizontalTransition.toInt()}")
                Slider(
                    value = leftEyeHorizontalTransition,
                    onValueChange = {
//                        leftEyeHorizontalTransition = it
                    },
                    valueRange = -50F..50F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "垂直偏移：${leftEyeVerticalTransition.toInt()}")
                Slider(
                    value = leftEyeVerticalTransition,
                    onValueChange = {
//                        leftEyeVerticalTransition = it
                    },
                    valueRange = -50F..50F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "旋转角度：${leftEyeRotatedAngle.toInt()}")
                Slider(
                    value = leftEyeRotatedAngle,
                    onValueChange = {
//                        leftEyeRotatedAngle = it
                    },
                    valueRange = -50F..50F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "边缘角度：$leftEyeCornerRadius")
                Slider(
                    value = leftEyeCornerRadius,
                    onValueChange = {
                        leftEyeCornerRadius = it
                    },
                    valueRange = 0F..100F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )
            }

            //both
            Column(
                modifier = Modifier.weight(1F).padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(text = "水平偏移：${bothEyesHorizontalTransition}")
                Slider(
                    value = bothEyesHorizontalTransition,
                    onValueChange = {
//                        bothEyesHorizontalTransition = it
                    },
                    valueRange = -50F..50F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "垂直偏移：${bothEyesVerticalTransition}")
                Slider(
                    value = bothEyesVerticalTransition,
                    onValueChange = {
//                        bothEyesVerticalTransition = it
                    },
                    valueRange = -50F..50F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "旋转角度：${eyesRotatedAngle}")
                Slider(
                    value = eyesRotatedAngle,
                    onValueChange = {
//                        bothEyesRotatedAngle = it
                    },
                    valueRange = -50F..50F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )
            }

            //right
            Column(
                modifier = Modifier.weight(1F).padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(text = "上眼皮高度：$rightUpperEyelidRadius")
                Slider(
                    value = rightUpperEyelidRadius,
                    onValueChange = {
//                        rightUpperEyelidHeight = it
                    },
                    valueRange = 0F..100F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "下眼皮高度：$rightLowerEyelidRadius")
                Slider(
                    value = rightLowerEyelidRadius,
                    onValueChange = {
//                        rightLowerEyelidHeight = it
                    },
                    valueRange = 0F..100F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )


                Text(text = "水平偏移：${rightEyeHorizontalTransition.toInt()}")
                Slider(
                    value = rightEyeHorizontalTransition,
                    onValueChange = {
//                        rightEyeHorizontalTransition = it
                    },
                    valueRange = -50F..50F,
                    steps = 19,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "垂直偏移：${rightEyeVerticalTransition.toInt()}")
                Slider(
                    value = rightEyeVerticalTransition,
                    onValueChange = {
//                        rightEyeVerticalTransition = it
                    },
                    valueRange = -50F..50F,
                    steps = 19,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Text(text = "旋转角度：${rightEyeRotatedAngle.toInt()}")
                Slider(
                    value = rightEyeRotatedAngle,
                    onValueChange = {
//                        rightEyeRotatedAngle = it
                    },
                    valueRange = -50F..50F,
                    steps = 29,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )


                Text(text = "边缘角度：$rightEyeCornerRadius")
                Slider(
                    value = rightEyeCornerRadius,
                    onValueChange = {
                        rightEyeCornerRadius = it
                    },
                    valueRange = 0F..100F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )
            }
        }

        Row(
            Modifier
                .padding(top = 180.dp)
                .size(200.dp, 200.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Color.Black)
                .align(Alignment.TopCenter),
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                translate(left = bothEyesHorizontalTransition, top = bothEyesVerticalTransition) {
                    scale(
                        scaleX = bothEyesScaleX,
                        scaleY = bothEyesScaleY,
                        pivot = eyesCenterPivot
                    ) {
                        rotate(
                            degrees = eyesRotatedAngle,
                            pivot = if (eyeState.useOffsetPivot) eyesCenterOffsetPivot else eyesCenterPivot,
                        ) {
                            drawEye(
                                center = leftEyeOffset,
                                horizontalRadius = eyeState.leftEyeHorizontalRadius,
                                upperEyelidRadius = leftUpperEyelidRadius,
                                lowerEyelidRadius = leftLowerEyelidRadius,
                                cornerRadius = leftEyeCornerRadius,
                                horizontalTranslation = leftEyeHorizontalTransition,
                                verticalTranslation = leftEyeVerticalTransition,
                                rotateAngle = leftEyeRotatedAngle,
                                fillColor = eyeState.fillColor
                            )

                            drawEye(
                                center = rightEyeOffset,
                                horizontalRadius = eyeState.rightEyeHorizontalRadius,
                                upperEyelidRadius = rightUpperEyelidRadius,
                                lowerEyelidRadius = rightLowerEyelidRadius,
                                cornerRadius = rightEyeCornerRadius,
                                horizontalTranslation = rightEyeHorizontalTransition,
                                verticalTranslation = rightEyeVerticalTransition,
                                rotateAngle = rightEyeRotatedAngle,
                                fillColor = eyeState.fillColor
                            )
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 500.dp)
                .align(Alignment.TopCenter)
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
