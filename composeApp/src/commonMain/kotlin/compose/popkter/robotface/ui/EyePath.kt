package compose.popkter.robotface.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import kotlin.math.abs


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