package compose.popkter.robotface.ui

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.Transition
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import compose.popkter.robotface.ext.calculateRotateCenterPivot
import compose.popkter.robotface.ext.generateTransition
import compose.popkter.robotface.status.RobotStatus


@Composable
fun RobotStatus.drawAction(
    modifier: Modifier,
    finiteTransition: Transition<RobotStatus>,
    infiniteTransition: InfiniteTransition,
    textMeasurer: TextMeasurer
) {
    val rotate by actionRotate.generateTransition(finiteTransition, infiniteTransition)
    val scale by actionScale.generateTransition(finiteTransition, infiniteTransition)
    val scaleX by actionScaleX.generateTransition(finiteTransition, infiniteTransition)
    val scaleY by actionScaleY.generateTransition(finiteTransition, infiniteTransition)
    val horizontalTransition by actionHorizontalTransition.generateTransition(finiteTransition, infiniteTransition)
    val verticalTransition by actionVerticalTransition.generateTransition(finiteTransition, infiniteTransition)

    val sampleRoate by actionSampleRotate.generateTransition(finiteTransition,infiniteTransition)
    if (actionSample.sample.isEmpty()) return


    Canvas(
        modifier = modifier
    ) {
        val textStyle = TextStyle(
            fontSize = 40.sp * scale,
            color = Color.White,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.ExtraBold,
        )

        val textLayoutResult = textMeasurer.measure(
            text = actionSample.sample,
            style = textStyle
        )

        translate(left = horizontalTransition, top = verticalTransition) {
            /**
             * action画布旋转角度
             */
            rotate(
                degrees = rotate,
                pivot = actionRotate.centerPivotLevel.calculateRotateCenterPivot(center, size.width / 2)
            ) {
                /**
                 * Sample自身旋转角度
                 */
                rotate(
                    degrees = sampleRoate,
                    pivot = center
                ) {
                    scale(scaleX, scaleY) {
                        drawRect(color = Color.Gray.copy(alpha = .5F), topLeft = Offset.Zero, size = size)
                        drawText(
                            textLayoutResult = textLayoutResult,
                            topLeft = Offset(
                                center.x - (textLayoutResult.size.width) / 2,
                                center.y - (textLayoutResult.size.height) / 2
                            ),
                            drawStyle = Stroke(width = 3F)
                        )
                    }
                }
            }
        }
    }
}
