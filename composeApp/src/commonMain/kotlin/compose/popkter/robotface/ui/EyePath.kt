package compose.popkter.robotface.ui

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import compose.popkter.robotface.status.ITransitionProperty
import compose.popkter.robotface.status.RobotStatus
import kotlin.math.abs


@Composable
fun drawEyes(
    modifier: Modifier,
    status: RobotStatus,
    infiniteTransition: InfiniteTransition
) {

    val leftEyeHorizontalRadius by generateEyeDetail(status.eyesSizeDetail.leftEyeHorizontalRadius)

    val rightEyeHorizontalRadius by generateEyeDetail(status.eyesSizeDetail.rightEyeHorizontalRadius)

    val leftUpperEyelidRadius by generateEyeDetail(status.eyesSizeDetail.leftUpperEyelidRadius)

    val leftLowerEyelidRadius by generateEyeDetail(status.eyesSizeDetail.leftLowerEyelidRadius)

    val rightUpperEyelidRadius by generateEyeDetail(status.eyesSizeDetail.rightUpperEyelidRadius)

    val rightLowerEyelidRadius by generateEyeDetail(status.eyesSizeDetail.rightLowerEyelidRadius)

    val leftEyeCornerRadius by generateEyeDetail(status.eyesSizeDetail.leftEyeCornerRadius)

    val rightEyeCornerRadius by generateEyeDetail(status.eyesSizeDetail.rightEyeCornerRadius)

    //Both Eyes Rotate
    val eyesRotatedAngle by generateTransition(infiniteTransition, status.rotate)

    //horizontal translation
    val eyesHorizontalTransition by generateTransition(infiniteTransition, status.horizontalTransition)

    //vertical translation
    val eyesVerticalTransition by generateTransition(infiniteTransition, status.verticalTransition)

    //scaleX
    val eyesScaleX by generateTransition(infiniteTransition, status.scaleX)

    //scaleY
    val eyesScaleY by generateTransition(infiniteTransition, status.scaleY)

    val leftEyeRotatedAngle by generateTransition(infiniteTransition, status.leftEyeRotate)

    val rightEyeRotatedAngle by generateTransition(infiniteTransition, status.rightEyeRotate)

    val leftEyeScaleX by generateTransition(infiniteTransition, status.leftEyeScaleX)

    val rightEyeScaleX by generateTransition(infiniteTransition, status.rightEyeScaleX)

    val leftEyeScaleY by generateTransition(infiniteTransition, status.leftEyeScaleY)

    val rightEyeScaleY by generateTransition(infiniteTransition, status.rightEyeScaleY)

    val leftEyeHorizontalTransition by generateTransition(infiniteTransition, status.leftEyeHorizontalTransition)

    val rightEyeHorizontalTransition by generateTransition(infiniteTransition, status.rightEyeHorizontalTransition)

    val leftEyeVerticalTransition by generateTransition(infiniteTransition, status.leftEyeVerticalTransition)

    val rightEyeVerticalTransition by generateTransition(infiniteTransition, status.rightEyeVerticalTransition)

    Canvas(modifier = modifier) {
        val faceRadius = size.width

        val leftEyeCenter = Offset(faceRadius / 3, faceRadius * 2 / 5)
        val rightEyeCenter = Offset(faceRadius * 2 / 3, faceRadius * 2 / 5)

        val eyesCenterPivot = Offset(faceRadius / 2, faceRadius * 2 / 5)
        val eyesCenterOffsetPivot = Offset(faceRadius / 2, faceRadius * 2 / 5 + 100)

        translate(left = eyesHorizontalTransition, top = eyesVerticalTransition) {
            scale(
                scaleX = eyesScaleX,
                scaleY = eyesScaleY,
                pivot = eyesCenterPivot
            ) {
                rotate(
                    degrees = eyesRotatedAngle,
                    pivot = if (status.rotate.useOffsetPivot) eyesCenterOffsetPivot else eyesCenterPivot,
                ) {
                    drawEye(
                        center = leftEyeCenter,
                        horizontalRadius = leftEyeHorizontalRadius,
                        upperEyelidRadius = leftUpperEyelidRadius,
                        lowerEyelidRadius = leftLowerEyelidRadius,
                        cornerRadius = leftEyeCornerRadius,
                        horizontalTranslation = leftEyeHorizontalTransition,
                        verticalTranslation = leftEyeVerticalTransition,
                        rotateAngle = leftEyeRotatedAngle,
                        scaleX = leftEyeScaleX,
                        scaleY = leftEyeScaleY,
                        fillColor = status.fillColor
                    )

                    drawEye(
                        center = rightEyeCenter,
                        horizontalRadius = rightEyeHorizontalRadius,
                        upperEyelidRadius = rightUpperEyelidRadius,
                        lowerEyelidRadius = rightLowerEyelidRadius,
                        cornerRadius = rightEyeCornerRadius,
                        horizontalTranslation = rightEyeHorizontalTransition,
                        verticalTranslation = rightEyeVerticalTransition,
                        rotateAngle = rightEyeRotatedAngle,
                        scaleX = rightEyeScaleX,
                        scaleY = rightEyeScaleY,
                        fillColor = status.fillColor
                    )
                }
            }
        }
    }
}

@Composable
fun generateTransition(infiniteTransition: InfiniteTransition, transition: ITransitionProperty) = with(transition) {
    if (infinite) {
        infiniteTransition.animateFloat(
            initialValue = initialValue,
            targetValue = targetValue,
            animationSpec = animationSpec as InfiniteRepeatableSpec<Float>
        )
    } else {
        animateFloatAsState(
            targetValue = targetValue,
            animationSpec = animationSpec
        )
    }
}

@Composable
fun generateEyeDetail(targetValue: Float) = animateFloatAsState(
    targetValue = targetValue,
    animationSpec = tween(durationMillis = 240, easing = LinearEasing)
)

/**
 * [center] Center Pivot
 * [horizontalRadius] horizontalRadius
 * [upperEyelidRadius] UpperEyelidHeight
 * [lowerEyelidRadius]LowerEyelidHeight
 */
fun DrawScope.drawEye(
    center: Offset,
    horizontalRadius: Float,
    upperEyelidRadius: Float,
    lowerEyelidRadius: Float,
    cornerRadius: Float = 0F,
    rotateAngle: Float = 0F,
    horizontalTranslation: Float = 0F,
    verticalTranslation: Float = 0F,
    scaleX: Float = 1F,
    scaleY: Float = 1F,
    fillColor: Color = Color.Transparent
) {

    val (x, y) = center

    val upperCornerRadius = listOf(abs(upperEyelidRadius), abs(cornerRadius), abs(horizontalRadius)).min()
    val lowerCornerRadius = listOf(abs(lowerEyelidRadius), abs(cornerRadius), abs(horizontalRadius)).min()


    val path = Path().apply {

        upperEyelidRadius.takeIf { it > 0 }?.let {
            moveTo(x - horizontalRadius, y)
            relativeLineTo(0F, -(upperEyelidRadius - upperCornerRadius))
            arcTo(
                rect = Rect(
                    center = Offset(x - (horizontalRadius - upperCornerRadius), y - (upperEyelidRadius - upperCornerRadius)),
                    radius = upperCornerRadius
                ),
                startAngleDegrees = 180F,
                sweepAngleDegrees = 90F,
                forceMoveTo = false
            )
            relativeLineTo(horizontalRadius - upperCornerRadius, 0F)
            arcTo(
                rect = Rect(
                    center = Offset(x + (horizontalRadius - upperCornerRadius), y - (upperEyelidRadius - upperCornerRadius)),
                    radius = upperCornerRadius
                ),
                startAngleDegrees = 270F,
                sweepAngleDegrees = 90F,
                forceMoveTo = false
            )
            relativeLineTo(0F, (upperEyelidRadius - upperCornerRadius))
        }

        lowerEyelidRadius.takeIf { it > 0 }?.let {
            moveTo(x - horizontalRadius, y)
            relativeLineTo(0F, (lowerEyelidRadius - lowerCornerRadius))
            arcTo(
                rect = Rect(
                    center = Offset(x - (horizontalRadius - lowerCornerRadius), y + (lowerEyelidRadius - lowerCornerRadius)),
                    radius = lowerCornerRadius
                ),
                startAngleDegrees = 180F,
                sweepAngleDegrees = -90F,
                forceMoveTo = false
            )
            relativeLineTo(horizontalRadius - lowerCornerRadius, 0F)
            arcTo(
                rect = Rect(
                    center = Offset(x + (horizontalRadius - lowerCornerRadius), y + (lowerEyelidRadius - lowerCornerRadius)),
                    radius = lowerCornerRadius
                ),
                startAngleDegrees = 90F,
                sweepAngleDegrees = -90F,
                forceMoveTo = false
            )
            relativeLineTo(0F, -(lowerEyelidRadius - lowerCornerRadius))
        }
    }

    translate(left = horizontalTranslation, top = verticalTranslation) {
        rotate(degrees = rotateAngle, pivot = center) {
            scale(scaleX = scaleX, scaleY = scaleY, pivot = center) {
                if (fillColor != Color.Transparent) {
                    drawPath(
                        path = path,
                        color = fillColor,
                        style = Fill
                    )
                }

                drawPath(
                    path = path,
                    color = Color.White,
                    style = Stroke(width = 10F)
                )
            }
        }
    }
}