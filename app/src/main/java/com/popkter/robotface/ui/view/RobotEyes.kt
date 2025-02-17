package com.popkter.robotface.ui.view

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import com.popkter.robotface.tool.dp
import com.popkter.robotface.tool.px

private const val TAG = "RobotEyes"

@Composable
fun RobotEyes(eyeState: EyeState) {

    val eyeHeight by animateFloatAsState(
        targetValue = when (eyeState) {
            EyeState.Normal -> 100F
            EyeState.Blink -> 20F
            else -> 0F
        },
        animationSpec = tween(durationMillis = 300)
    )

    val sweepAngle by animateFloatAsState(
        targetValue = when (eyeState) {
            EyeState.Cry -> 180F
            EyeState.Smile -> -180F
            else -> 360F
        },
        animationSpec = tween(durationMillis = 300)
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Canvas(modifier = Modifier.size(300.px.dp)) {
            val eyeRadius = 50F
            val eyeSpacing = 150F

            drawRect(
                color = Color.Gray,
                size = size,
                style = Fill,
                topLeft = Offset(0F, 0F)
            )

            Log.e(TAG, "RobotEyes: ${center.x} ${center.y} eyeSpacing: $eyeSpacing eyeRadius: $eyeRadius")
            when (eyeState) {
                EyeState.Normal,
                EyeState.Blink -> {

                    // 左眼
                    drawArc(
                        color = Color.Black,
                        startAngle = 0F,
                        sweepAngle = 360F,
                        useCenter = true,
                        topLeft = Offset(center.x - eyeSpacing, center.y + (100 - eyeHeight) / 2),
                        size = Size(eyeRadius * 2, eyeHeight),
                        style = Fill,
                    )

                    // 右眼
                    drawArc(
                        color = Color.Black,
                        startAngle = 0F,
                        sweepAngle = 360F,
                        useCenter = true,
                        topLeft = Offset(
                            size.width - (center.x - eyeSpacing) - eyeRadius * 2,
                            center.y + (100 - eyeHeight) / 2
                        ),
                        size = Size(eyeRadius * 2, eyeHeight),
                        style = Fill,
                    )
                }

                EyeState.Smile -> {


                    drawArc(
                        color = Color.Black,
                        startAngle = 0F,
                        sweepAngle = -180F,
                        useCenter = true,
                        topLeft = Offset(center.x - eyeSpacing, center.y + 0F),
                        size = Size(eyeRadius * 2, 100F),
                        style = Fill,
                    )

                    drawArc(
                        color = Color.Gray,
                        startAngle = 0F,
                        sweepAngle = -180F,
                        useCenter = true,
                        topLeft = Offset(center.x - eyeSpacing + 8, center.y + 8F),
                        size = Size(eyeRadius * 2 - 16, 100F),
                        style = Fill,
                    )

                    drawArc(
                        color = Color.Black,
                        startAngle = 0F,
                        sweepAngle = -180F,
                        useCenter = true,
                        topLeft = Offset(size.width - (center.x - eyeSpacing) - eyeRadius * 2, center.y + 0F),
                        size = Size(eyeRadius * 2, 100F),
                        style = Fill,
                    )

                    drawArc(
                        color = Color.Gray,
                        startAngle = 0F,
                        sweepAngle = -180F,
                        useCenter = true,
                        topLeft = Offset(size.width - (center.x - eyeSpacing) - eyeRadius * 2 + 8, center.y + 8F),
                        size = Size(eyeRadius * 2 - 16, 100F),
                        style = Fill,
                    )
                }

                EyeState.Cry -> {
                    drawArc(
                        color = Color.Black,
                        startAngle = 0F,
                        sweepAngle = 180F,
                        useCenter = true,
                        topLeft = Offset(center.x - eyeSpacing, center.y),
                        size = Size(eyeRadius * 2, 100F),
                        style = Fill,
                    )

                    drawArc(
                        color = Color.Gray,
                        startAngle = 0F,
                        sweepAngle = 180F,
                        useCenter = true,
                        topLeft = Offset(center.x - eyeSpacing + 8, center.y - 8F),
                        size = Size(eyeRadius * 2 - 16, 100F),
                        style = Fill,
                    )


                    drawArc(
                        color = Color.Black,
                        startAngle = 0F,
                        sweepAngle = 180F,
                        useCenter = true,
                        topLeft = Offset(size.width - (center.x - eyeSpacing) - eyeRadius * 2, center.y),
                        size = Size(eyeRadius * 2, 100F),
                        style = Fill,
                    )

                    drawArc(
                        color = Color.Gray,
                        startAngle = 0F,
                        sweepAngle = 180F,
                        useCenter = true,
                        topLeft = Offset(size.width - (center.x - eyeSpacing) - eyeRadius * 2 + 8F, center.y - 8F),
                        size = Size(eyeRadius * 2 - 16, 100F),
                        style = Fill,
                    )
                }

            }
        }
    }
}

enum class EyeState { Normal, Blink, Smile, Cry }
