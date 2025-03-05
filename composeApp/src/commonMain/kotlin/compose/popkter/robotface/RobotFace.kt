package compose.project.demo

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
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
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview


sealed class EyeState(val name: String) {
    data object Ordinary : EyeState("Ordinary")
    data object Blink : EyeState("Blink")
    data object Smile : EyeState("Smile")
    data object Cry : EyeState("Cry")
    data object FingerHeart : EyeState("FingerHeart")
    data object PlayMusic : EyeState("PlayMusic")
    data object ColdSweat : EyeState("ColdSweat")
    data object OKay : EyeState("OKay")
    data object Mute : EyeState("Mute")
    data object Angry : EyeState("Angry")

    companion object {
        val allStates by lazy { listOf(Ordinary, Blink, Smile, Cry, FingerHeart, PlayMusic, ColdSweat, OKay, Mute, Angry) }

        fun EyeState.canBlinkState(): Boolean {
            return this in arrayOf(Ordinary, Blink)
        }
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

    val eyeSweepAngle = EyelidPosition.allPosition.associateWith { key ->
        animateFloatAsState(
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

    val coldSweatTopTranslate by animateFloatAsState(
        targetValue = when (eyeState) {
            EyeState.ColdSweat -> 100F
            else -> 0F
        },
        animationSpec = tween(durationMillis = 1200, easing = LinearOutSlowInEasing)
    )

    val coldSweatLeftTranslate by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val smileTopTranslate by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 600, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val muteRotate by infiniteTransition.animateFloat(
        initialValue = -2F,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val angryEyeBrowSweepAngle by animateFloatAsState(
        targetValue = when (eyeState) {
            EyeState.Angry -> 30F
            else -> 0F
        },
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
    )

    val angryMouthSweepAngle by animateFloatAsState(
        targetValue = when (eyeState) {
            EyeState.Angry -> 180F
            else -> 0F
        },
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
    )

    val angryTopTranslate by animateFloatAsState(
        targetValue = when (eyeState) {
            EyeState.Angry -> 50F
            else -> 0F
        },
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
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

    val graphicsLayer = rememberGraphicsLayer()
    
    Canvas(modifier = Modifier.size(200.dp)) {
        //Face
        drawOval(
            color = Color.Black, size = Size(
                size.width,
                size.height
            ), topLeft = Offset(0f, 0f)
        )

        //Eyes & Action
        when (eyeState) {
            EyeState.FingerHeart -> {

                rotate(fingerHeartRotate, pivot = Offset(center.x, center.y)) {
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }

                rotate(fingerHeartRotate, pivot = Offset(center.x, center.y + 800)) {
                    drawHeart(center)
                }
            }

            EyeState.PlayMusic -> {
                rotate(degrees = playMusicRotate, pivot = Offset(center.x, center.y)) {
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }

                rotate(playMusicRotate, pivot = Offset(center.x, center.y + 400)) {
                    drawTrebleClef(center, textOffsetSize, textScaleSize, textMeasurer)
                }
            }

            EyeState.OKay -> {
                rotate(
                    degrees = okayRotate,
                    pivot = Offset(if (eyeDegrees > 0) center.x + 100 else center.x - 100, center.y)
                ) {
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }
                rotate(
                    degrees = playMusicRotate,
                    pivot = Offset(center.x - 100F, center.y + 90F)
                ) {
                    drawOkay(center)
                }
            }

            EyeState.ColdSweat -> {
                translate(left = coldSweatLeftTranslate, top = coldSweatTopTranslate) {
                    drawColdSweat(center)
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }
            }

            EyeState.Mute -> {
                drawMute(center, size)
                rotate(degrees = muteRotate, pivot = Offset(center.x, center.y)) {
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }
            }

            EyeState.Smile -> {
                translate(left = 0F, top = smileTopTranslate) {
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
                }
            }

            EyeState.Angry -> {
                translate(left = 0F, top = angryTopTranslate) {
                    drawAngry(center, size, angryEyeBrowSweepAngle, angryMouthSweepAngle)
                    drawEyes(eyeSpacing, leftEyeSize, rightEyeSize, eyeSweepAngle, eyesOffsetHeight)
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
    MaterialTheme {
        Column(modifier = Modifier.wrapContentSize()) {
//            RobotFace(EyeState.Ordinary)
            RobotFace(EyeState.OKay)
            RobotFace(EyeState.ColdSweat)
            RobotFace(EyeState.Angry)
            RobotFace(EyeState.FingerHeart)
        }
    }
}