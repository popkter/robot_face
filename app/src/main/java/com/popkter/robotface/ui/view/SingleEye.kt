package com.popkter.robotface.ui.view

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke

/**
 * Draw Single Eye by Canvas
 *
 * @param size  Eyes Active Area Size
 * @param center Eyes Center Point
 * @param th  UpperEyeLid Vertical Line Height
 * @param tlw UpperEyeLid Horizontal Left Line Height
 * @param trw UpperEyeLid Horizontal Left Line Height
 * @param bh  LowerEyeLid Vertical Line Height
 * @param blw LowerEyeLid Horizontal Left Line Height
 * @param btw LowerEyeLid Horizontal Left Line Height
 */
@Composable
fun SingleEye(
    eyesWidth: Float,
    upperEyelidHeight: Float,
    lowerEyelidHeight: Float,
    center: Offset,
    th: Float = 0F,
    tlw: Float = 0F,
    trw: Float = 0F,
    bh: Float = 0F,
    blw: Float = 0F,
    btw: Float = 0F
) {
    Canvas(modifier = Modifier) {

        val rx = eyesWidth / 6
        val tRy = upperEyelidHeight / 3
        val bRy = lowerEyelidHeight / 3

        val path = Path().apply {
            moveTo(center.x, center.y)


            tRy.takeIf { it > 0 }?.let {
                relativeMoveTo(-rx, 0F)
                relativeLineTo(0F, -th)
                relativeQuadraticTo(0F, -(tRy - th), rx - tlw, -(tRy - th))
                relativeLineTo(tlw + trw, 0F)
                relativeQuadraticTo(rx - trw, 0F, rx - trw, tRy - th)
                relativeLineTo(0F, th )
            }



            bRy.takeIf { it > 0 }?.let {
                moveTo(center.x + rx, center.y)
                relativeLineTo(0F, bh)
                relativeQuadraticTo(0F, bRy - bh, -(rx - btw), bRy - bh)
                relativeLineTo(-(blw + btw), 0F)
                relativeQuadraticTo(-(rx - blw), 0F, -(rx - blw), -(bRy - bh))
                relativeLineTo(0F, -bh)
            }

//            close()
        }

        // 绘制 Path
        drawPath(
            path = path,
            color = Color.White,
            style = Stroke(width = 10F)
        )

    }
}