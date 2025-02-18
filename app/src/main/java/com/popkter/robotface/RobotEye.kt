package com.popkter.robotface

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

fun DrawScope.drawEyes(
    eyeSpacing: Float,
    leftEyeSize: Size,
    rightEyeSize: Size,
    eyeSweepAngle: Map<EyelidPosition, Float>,
    eyesOffsetHeight: Float,
    eyesColor: Color = Color.White
) {
    drawPath(
        path = Path().apply {
            arcTo(
                rect = Rect(
                    left = center.x - eyeSpacing - leftEyeSize.width,
                    top = center.y - leftEyeSize.height / 2 + eyesOffsetHeight,
                    right = center.x - eyeSpacing,
                    bottom = center.y + leftEyeSize.height / 2 + eyesOffsetHeight
                ),
                startAngleDegrees = 0F,
                sweepAngleDegrees = eyeSweepAngle[EyelidPosition.LeftUpEyelid]!!,
                forceMoveTo = true
            )
            arcTo(
                rect = Rect(
                    left = center.x - eyeSpacing - leftEyeSize.width,
                    top = center.y - leftEyeSize.height / 2 + eyesOffsetHeight,
                    right = center.x - eyeSpacing,
                    bottom = center.y + leftEyeSize.height / 2 + eyesOffsetHeight
                ),
                startAngleDegrees = 0F,
                sweepAngleDegrees = eyeSweepAngle[EyelidPosition.LeftLowEyelid]!!,
                forceMoveTo = true
            )
            arcTo(
                rect = Rect(
                    left = center.x + eyeSpacing,
                    top = center.y - rightEyeSize.height / 2 + eyesOffsetHeight,
                    right = center.x + eyeSpacing + rightEyeSize.width,
                    bottom = center.y + rightEyeSize.height / 2 + eyesOffsetHeight
                ),
                startAngleDegrees = 0F,
                sweepAngleDegrees = eyeSweepAngle[EyelidPosition.RightUpEyelid]!!,
                forceMoveTo = true
            )
            arcTo(
                rect = Rect(
                    left = center.x + eyeSpacing,
                    top = center.y - rightEyeSize.height / 2 + eyesOffsetHeight,
                    right = center.x + eyeSpacing + rightEyeSize.width,
                    bottom = center.y + rightEyeSize.height / 2 + eyesOffsetHeight
                ),
                startAngleDegrees = 0F,
                sweepAngleDegrees = eyeSweepAngle[EyelidPosition.RightLowEyelid]!!,
                forceMoveTo = true
            )
        },
        color = eyesColor,
        style = Stroke(width = 20F)
    )
}