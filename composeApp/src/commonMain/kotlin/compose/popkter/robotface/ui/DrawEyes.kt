package compose.popkter.robotface.ui

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.Transition
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
import compose.popkter.robotface.ext.calculateRotateCenterPivot
import compose.popkter.robotface.ext.generateTransition
import compose.popkter.robotface.status.RobotStatus
import kotlin.math.abs

/**
 * draw both eyes
 */
@Composable
fun RobotStatus.drawEyes(
    modifier: Modifier,
    finiteTransition: Transition<RobotStatus>,
    infiniteTransition: InfiniteTransition
) {
    val leftEyeHorizontalRadius by leftEyeHorizontalRadius.generateTransition(finiteTransition, infiniteTransition)

    val rightEyeHorizontalRadius by rightEyeHorizontalRadius.generateTransition(finiteTransition, infiniteTransition)

    val leftUpperEyelidRadius by leftTopEyeRadius.generateTransition(finiteTransition, infiniteTransition)

    val leftLowerEyelidRadius by leftBottomEyeRadius.generateTransition(finiteTransition, infiniteTransition)

    val rightUpperEyelidRadius by rightTopEyeRadius.generateTransition(finiteTransition, infiniteTransition)

    val rightLowerEyelidRadius by rightBottomEyeRadius.generateTransition(finiteTransition, infiniteTransition)

    val leftEyeCornerRadius by leftEyeCornerRadius.generateTransition(finiteTransition, infiniteTransition)

    val rightEyeCornerRadius by rightEyeCornerRadius.generateTransition(finiteTransition, infiniteTransition)

    //both eyes rotate
    val eyesRotatedAngle by eyesRotate.generateTransition(finiteTransition, infiniteTransition)

    //both eyes horizontal translation
    val eyesHorizontalTransition by eyesHorizontalTransition.generateTransition(finiteTransition, infiniteTransition)

    //both eyes vertical translation
    val eyesVerticalTransition by eyesVerticalTransition.generateTransition(finiteTransition, infiniteTransition)

    //both eyes scaleX
    val eyesScaleX by eyesScaleX.generateTransition(finiteTransition, infiniteTransition)

    //both eyes scaleY
    val eyesScaleY by eyesScaleY.generateTransition(finiteTransition, infiniteTransition)

    val leftEyeRotatedAngle by leftEyeRotate.generateTransition(finiteTransition, infiniteTransition)

    val rightEyeRotatedAngle by rightEyeRotate.generateTransition(finiteTransition, infiniteTransition)

    val leftEyeScaleX by leftEyeScaleX.generateTransition(finiteTransition, infiniteTransition)

    val rightEyeScaleX by rightEyeScaleX.generateTransition(finiteTransition, infiniteTransition)

    val leftEyeScaleY by leftEyeScaleY.generateTransition(finiteTransition, infiniteTransition)

    val rightEyeScaleY by rightEyeScaleY.generateTransition(finiteTransition, infiniteTransition)

    val leftEyeHorizontalTransition by leftEyeHorizontalTransition.generateTransition(finiteTransition, infiniteTransition)

    val rightEyeHorizontalTransition by rightEyeHorizontalTransition.generateTransition(finiteTransition, infiniteTransition)

    val leftEyeVerticalTransition by leftEyeVerticalTransition.generateTransition(finiteTransition, infiniteTransition)

    val rightEyeVerticalTransition by rightEyeVerticalTransition.generateTransition(finiteTransition, infiniteTransition)

    Canvas(modifier = modifier) {

        val faceRadius = size.width

        val leftEyeCenter = Offset(faceRadius / 3, faceRadius * 2 / 5)
        val rightEyeCenter = Offset(faceRadius * 2 / 3, faceRadius * 2 / 5)

        val eyesCenterPivot = Offset(faceRadius / 2, faceRadius * 2 / 5)

        translate(left = eyesHorizontalTransition, top = eyesVerticalTransition) {
            rotate(
                degrees = eyesRotatedAngle,
                pivot = eyesRotate.centerPivotLevel.calculateRotateCenterPivot(center, size.width / 2)
            ) {
                scale(
                    scaleX = eyesScaleX,
                    scaleY = eyesScaleY,
                    pivot = eyesCenterPivot
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
                        fillColor = eyesFillColor
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
                        fillColor = eyesFillColor
                    )
                }
            }
        }
    }

}
/**
 * draw single eye
 * [center] Center Pivot
 * [horizontalRadius] horizontalRadius
 * [upperEyelidRadius] UpperEyelidHeight
 * [lowerEyelidRadius]LowerEyelidHeight
 */
internal fun DrawScope.drawEye(
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

