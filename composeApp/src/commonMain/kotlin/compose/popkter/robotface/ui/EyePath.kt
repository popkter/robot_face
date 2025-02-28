package compose.popkter.robotface.ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import kotlin.math.min


/**
 * [center] Center Pivot
 * [width] EyeTotalWidth
 * [upperEyelidRadius] UpperEyelidHeight
 * [lowerEyelidRadius]LowerEyelidHeight
 */
fun DrawScope.drawEye(
    center: Offset,
    width: Float,
    upperEyelidRadius: Float,
    lowerEyelidRadius: Float,
    topRadius: Float = 0F,
    bottomRadius: Float = 0F,
    horizontalTranslation: Float = 0F,
    verticalTranslation: Float = 0F,
    rotateAngle: Float = 0F
) {

    val horizontalRadius = width / 2
    val x = center.x
    val y = center.y

//    //此处需要通过topRadius计算th
//    val th = 0F
//    //此处需要通过topRadius计算tlw
//    val tlw = 0F
//    //此处需要通过topRadius计算trw
//    val trw = 0F
//
//    //此处需要通过topRadius计算th
//    val bh = 0F
//    //此处需要通过topRadius计算blw
//    val blw = 0F
//    //此处需要通过topRadius计算brw
//    val brw = 0F


    // 贝塞尔圆弧近似系数
    val k = 0.552F

    // 通过 topRadius 计算贝塞尔终点和控制点
    val th = min(k * topRadius, upperEyelidRadius)
    val tlw = min(horizontalRadius, horizontalRadius - k * topRadius)
    val trw = min(horizontalRadius, horizontalRadius - k * topRadius)

    val bh = min(k * bottomRadius, lowerEyelidRadius)
    val blw = min(horizontalRadius, horizontalRadius - k * bottomRadius)
    val brw = min(horizontalRadius, horizontalRadius - k * bottomRadius)


    val path = Path().apply {

        upperEyelidRadius.takeIf { it > 0 }?.let {
            moveTo(x - horizontalRadius, y)

            lineTo(x - horizontalRadius, y - th)
            quadraticTo(x - horizontalRadius, y - upperEyelidRadius, x - tlw, y - upperEyelidRadius)
            lineTo(x, y - upperEyelidRadius)

            lineTo(x + trw, y - upperEyelidRadius)
            quadraticTo(x + horizontalRadius, y - upperEyelidRadius, x + horizontalRadius, y - th)
            lineTo(x + horizontalRadius, y)
        }

        lowerEyelidRadius.takeIf { it > 0 }?.let {
            moveTo(x + horizontalRadius, y)

            lineTo(x + horizontalRadius, y + bh)
            quadraticTo(x + horizontalRadius, y + lowerEyelidRadius, x + brw, y + lowerEyelidRadius)
            lineTo(x, y + lowerEyelidRadius)

            lineTo(x - blw, y + lowerEyelidRadius)
            quadraticTo(x - horizontalRadius, y + lowerEyelidRadius, x - horizontalRadius, y + bh)
            lineTo(x - horizontalRadius, y)
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