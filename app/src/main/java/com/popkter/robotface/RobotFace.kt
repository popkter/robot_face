package com.popkter.robotface

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.popkter.robotface.ui.theme.RobotFaceTheme


sealed class EyeState(val name: String) {
    data object Ordinary : EyeState("Ordinary")
    data object Blink : EyeState("Blink")
    data object Smile : EyeState("Smile")
    data object Cry : EyeState("Cry")
    data object FingerHeart : EyeState("FingerHeart")
    data object PlayMusic : EyeState("PlayMusic")
    data object ColdSweat : EyeState("ColdSweat")
    data object OKay : EyeState("OKay")

    companion object {
        val allStates by lazy { listOf(Ordinary, Blink, Smile, Cry, FingerHeart, PlayMusic, ColdSweat, OKay) }
    }
}

sealed class EyelidPosition(val name: String) {
    data object LeftUpEyelid : EyelidPosition("LeftUpperEyelid")
    data object LeftLowEyelid : EyelidPosition("LeftLowerEyelid")
    data object RightUpEyelid : EyelidPosition("RightUpperEyelid")
    data object RightLowEyelid : EyelidPosition("RightUpperEyelid")
    companion object {
        val allPosition by lazy {
            listOf(
                LeftUpEyelid,
                LeftLowEyelid,
                RightUpEyelid,
                RightLowEyelid,
            )
        }
    }
}


@Composable
fun RobotFace(eyeState: EyeState) {

    val leftEyeSize by animateSizeAsState(
        targetValue = calculateLeftEyeSize(eyeState),
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val rightEyeSize by animateSizeAsState(
        targetValue = calculateRightEyeSize(eyeState),
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val eyeSpacing by animateFloatAsState(
        targetValue = calculateEyeSpacing(eyeState),
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val eyeDegrees by animateFloatAsState(
        targetValue = calculateEyeDegrees(eyeState),
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val eyesOffsetHeight by animateFloatAsState(
        targetValue = calculateEyeOffsetHeight(eyeState),
        animationSpec = tween(durationMillis = 300), label = ""
    )

    val eyeSweepAngle = EyelidPosition.allPosition.associate { key ->
        key to animateFloatAsState(
            targetValue = calculateEyesSweepAngle(key, eyeState),
            animationSpec = tween(durationMillis = 300),
            label = ""
        ).value
    }


    val infiniteTransition = rememberInfiniteTransition()

    val fingerHeartRotate by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val playMusicRotate by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val okayRotate by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )


    val textMeasurer = rememberTextMeasurer()
    val textOffsetSize by infiniteTransition.animateFloat(
        initialValue = 80F,
        targetValue = 60F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val textScaleSize by infiniteTransition.animateFloat(
        initialValue = 0.9F,
        targetValue = 1.1F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = Modifier.size(200.dp)) {
        //Face
        drawOval(
            color = Color.Black, size = Size(
                size.width,
                size.height
            ), topLeft = Offset(0f, 0f)
        )

        //Eyes

        //Action
        when (eyeState) {
            EyeState.FingerHeart -> {
                rotate(
                    degrees = eyeDegrees,
                    pivot = Offset(if (eyeDegrees > 0) center.x + 100 else center.x - 100, center.y)
                ) {
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }

                rotate(fingerHeartRotate, pivot = Offset(center.x, center.y + 800)) {
                    drawHeart(center.x, center.y)
                }
            }

            EyeState.PlayMusic -> {
                rotate(degrees = playMusicRotate, pivot = Offset(center.x, center.y)) {
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }

                rotate(playMusicRotate, pivot = Offset(center.x, center.y + 400)) {
                    drawTrebleClef(center.x, center.y, textOffsetSize, textScaleSize, textMeasurer)
                }
            }

            EyeState.OKay -> {
//                drawOkay(center.x, center.y)
//                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
//
                rotate(
                    degrees = okayRotate,
                    pivot = Offset(if (eyeDegrees > 0) center.x + 100 else center.x - 100, center.y)
                ) {
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }
                rotate(
                    degrees = playMusicRotate,
                    pivot = Offset(center.x-100F,center.y+90F)
                ) {
                    drawOkay(center.x, center.y)
                }
            }

            else -> {
                rotate(
                    degrees = eyeDegrees,
                    pivot = Offset(if (eyeDegrees > 0) center.x + 100 else center.x - 100, center.y)
                ) {
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }
            }
        }

    }
}

@Preview()
@Composable
fun GreetingPreview() {
    RobotFaceTheme {
        Column(modifier = Modifier.wrapContentSize()) {
//            RobotFace(EyeState.Ordinary)
            RobotFace(EyeState.OKay)
            RobotFace(EyeState.ColdSweat)
//            RobotFace(EyeState.PlayMusic)
            RobotFace(EyeState.FingerHeart)
        }
    }
}