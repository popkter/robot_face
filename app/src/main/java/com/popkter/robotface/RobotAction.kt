package com.popkter.robotface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.popkter.robotface.ui.theme.RobotFaceTheme


/**
 * 绘制心形
 */
fun DrawScope.drawHeart(centerX: Float, centerY: Float) {
    drawPath(
        path = Path().apply {
            moveTo(centerX, centerY)
            arcTo(
                rect = Rect(centerX, centerY + 40, centerX + 75, centerY + 115),
                startAngleDegrees = 180F,
                sweepAngleDegrees = 240F,
                forceMoveTo = false
            )
            lineTo(centerX, centerY + 155)
            moveTo(centerX, centerY)
            arcTo(
                rect = Rect(centerX - 75, centerY + 40, centerX, centerY + 115),
                startAngleDegrees = 0F,
                sweepAngleDegrees = -240F,
                forceMoveTo = false
            )
            lineTo(centerX, centerY + 155)
        },
        color = Color.White,
        style = Fill
    )
}


/**
 * 绘制文字音符
 */
fun DrawScope.drawTrebleClef(
    centerX: Float,
    centerY: Float,
    textOffset: Float,
    textScale: Float,
    textMeasurer: TextMeasurer
) {
    val text = "\uD834\uDD1E" // 🎼 高音谱号
    val text2 = "♫"             // 单音符
    val text3 = "♬"             // 双音符

    val textStyle = TextStyle(
        fontSize = 40.sp,
        color = Color.White,
        fontFamily = FontFamily.Monospace
    )

    val textLayoutResult1 = textMeasurer.measure(
        text = text,
        style = textStyle
    )

    scale(
        textScale,
        textScale,
        pivot = Offset(centerX - textLayoutResult1.size.width / 2, centerY - textLayoutResult1.size.height / 2)
    ) {
        drawText(
            textLayoutResult = textLayoutResult1,
            color = Color.White,
            topLeft = Offset(centerX - textLayoutResult1.size.width / 2, centerY + textLayoutResult1.size.height / 5)
        )
    }

    val textLayoutResult2 = textMeasurer.measure(
        text = text2,
        style = textStyle
    )

    drawText(
        textLayoutResult = textLayoutResult2,
        color = Color.White,
        topLeft = Offset(
            centerX - textLayoutResult2.size.width / 2 - textOffset,
            centerY + textLayoutResult2.size.height / 4
        )
    )

    val textLayoutResult3 = textMeasurer.measure(
        text = text3,
        style = textStyle
    )

    drawText(
        textLayoutResult = textLayoutResult3,
        color = Color.White,
        topLeft = Offset(
            centerX - textLayoutResult3.size.width / 2 + textOffset,
            centerY + textLayoutResult3.size.height / 5
        )
    )
}


/**
 * 绘制OK手势
 */
fun DrawScope.drawOkay(
    centerX: Float,
    centerY: Float
) {

    drawRoundRect(
        color = Color.White,
        size = Size(100F, 80F),
        cornerRadius = CornerRadius(40F, 40F),
        topLeft = Offset(centerX - 150F, centerY + 50F)
    )

    drawRoundRect(
        color = Color.White,
        size = Size(20F, 70F),
        cornerRadius = CornerRadius(20F, 20F),
        topLeft = Offset(centerX - 150F, centerY + 20F)
    )
    drawRoundRect(
        color = Color.White,
        size = Size(20F, 70F),
        cornerRadius = CornerRadius(20F, 20F),
        topLeft = Offset(centerX - 130F, centerY + 10F)
    )
    drawRoundRect(
        color = Color.White,
        size = Size(20F, 70F),
        cornerRadius = CornerRadius(20F, 20F),
        topLeft = Offset(centerX - 110F, centerY)
    )
    drawRoundRect(
        color = Color.Black,
        size = Size(25F, 50F),
        cornerRadius = CornerRadius(12.5F, 25F),
        topLeft = Offset(centerX - 90F, centerY + 65F)
    )
}


@Preview()
@Composable
fun OKayPreview() {
    RobotFaceTheme {
        Column(modifier = Modifier.wrapContentSize()) {
            RobotFace(EyeState.OKay)
        }
    }
}
