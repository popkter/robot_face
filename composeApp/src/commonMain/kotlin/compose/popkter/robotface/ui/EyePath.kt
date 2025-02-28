package compose.popkter.robotface.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import kotlin.math.max
import kotlin.math.min


/**
 * [center] Center Pivot
 * [width] EyeTotalWidth
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
    verticalTranslation: Float = 0F
) {

    val (x, y) = center

    val upperCornerRadius = max(0F, listOf(upperEyelidRadius, cornerRadius, horizontalRadius).min())
    val lowerCornerRadius = max(0F, listOf(lowerEyelidRadius, cornerRadius, horizontalRadius).min())


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
            drawPath(
                path = path,
                color = Color.White,
                style = Stroke(width = 10F)
            )
        }
    }
}