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
import androidx.compose.ui.graphics.drawscope.rotate
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
fun DrawScope.drawHeart(center: Offset) {
    drawPath(
        path = Path().apply {
            moveTo(center.x, center.y)
            arcTo(
                rect = Rect(center.x, center.y + 40, center.x + 75, center.y + 115),
                startAngleDegrees = 180F,
                sweepAngleDegrees = 240F,
                forceMoveTo = false
            )
            lineTo(center.x, center.y + 155)
            moveTo(center.x, center.y)
            arcTo(
                rect = Rect(center.x - 75, center.y + 40, center.x, center.y + 115),
                startAngleDegrees = 0F,
                sweepAngleDegrees = -240F,
                forceMoveTo = false
            )
            lineTo(center.x, center.y + 155)
        },
        color = Color.White,
        style = Fill
    )
}


/**
 * 绘制文字音符
 */
fun DrawScope.drawTrebleClef(
    center: Offset,
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
        pivot = Offset(center.x - textLayoutResult1.size.width / 2, center.y - textLayoutResult1.size.height / 2)
    ) {
        drawText(
            textLayoutResult = textLayoutResult1,
            color = Color.White,
            topLeft = Offset(center.x - textLayoutResult1.size.width / 2, center.y + textLayoutResult1.size.height / 5)
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
            center.x - textLayoutResult2.size.width / 2 - textOffset,
            center.y + textLayoutResult2.size.height / 4
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
            center.x - textLayoutResult3.size.width / 2 + textOffset,
            center.y + textLayoutResult3.size.height / 5
        )
    )
}


/**
 * 绘制OK手势
 */
fun DrawScope.drawOkay(
    center: Offset
) {

    drawRoundRect(
        color = Color.White,
        size = Size(100F, 80F),
        cornerRadius = CornerRadius(40F, 40F),
        topLeft = Offset(center.x - 150F, center.y + 50F)
    )

    drawRoundRect(
        color = Color.White,
        size = Size(20F, 70F),
        cornerRadius = CornerRadius(20F, 20F),
        topLeft = Offset(center.x - 150F, center.y + 20F)
    )
    drawRoundRect(
        color = Color.White,
        size = Size(20F, 70F),
        cornerRadius = CornerRadius(20F, 20F),
        topLeft = Offset(center.x - 130F, center.y + 10F)
    )
    drawRoundRect(
        color = Color.White,
        size = Size(20F, 70F),
        cornerRadius = CornerRadius(20F, 20F),
        topLeft = Offset(center.x - 110F, center.y)
    )
    drawRoundRect(
        color = Color.Black,
        size = Size(25F, 50F),
        cornerRadius = CornerRadius(12.5F, 25F),
        topLeft = Offset(center.x - 90F, center.y + 65F)
    )
}


/**
 * 绘制流汗
 */
fun DrawScope.drawColdSweat(center: Offset) {
    drawRoundRect(
        color = Color.White,
        size = Size(15F, 100F),
        topLeft = Offset(80F, 20F),
        cornerRadius = CornerRadius(5F, 10F),
        style = Fill
    )
    drawRoundRect(
        color = Color.White,
        size = Size(15F, 100F),
        topLeft = Offset(105F, 20F),
        cornerRadius = CornerRadius(5F, 10F),
        style = Fill
    )
    drawRoundRect(
        color = Color.White,
        size = Size(15F, 100F),
        topLeft = Offset(130F, 20F),
        cornerRadius = CornerRadius(5F, 10F),
        style = Fill
    )
}

/**
 * 绘制勿扰
 */
fun DrawScope.drawMute(center: Offset, size: Size) {
    drawArc(
        color = Color.White,
        startAngle = 10F,
        sweepAngle = -200F,
        useCenter = false,
        size = size - Size(50F, 50F),
        topLeft = Offset(30F, 30F),
        style = Stroke(width = 15F),
    )
    drawRoundRect(
        color = Color.White,
        size = Size(30F, 60F),
        cornerRadius = CornerRadius(5F, 10F),
        topLeft = Offset(size.width - 45, center.y)
    )
    drawRoundRect(
        color = Color.White,
        size = Size(30F, 60F),
        cornerRadius = CornerRadius(5F, 10F),
        topLeft = Offset(20F, center.y)
    )
}


/**
 * 绘制生气
 */
fun DrawScope.drawAngry(center: Offset, size: Size, eyebrowSweepAngle: Float, mouthSweepAngle: Float) {

    rotate(degrees = eyebrowSweepAngle, pivot = Offset(center.x - 120F, center.y - 150F)) {
        drawRoundRect(
            color = Color.White,
            size = Size(110F, 20F),
            cornerRadius = CornerRadius(10F, 10F),
            topLeft = Offset(center.x - 120F, center.y - 150F),
            style = Fill
        )
    }

    rotate(degrees = -eyebrowSweepAngle, pivot = Offset(center.x + 130F, center.y - 150F)) {
        drawRoundRect(
            color = Color.White,
            size = Size(110F, 20F),
            cornerRadius = CornerRadius(10F, 10F),
            topLeft = Offset(center.x + 20F, center.y - 150F),
            style = Fill
        )
    }

    drawArc(
        color = Color.White,
        startAngle = 180F,
        sweepAngle = mouthSweepAngle,
        useCenter = false,
        size = Size(160F, 70F),
        topLeft = Offset(center.x - 80F, center.y + 35F),
        style = Stroke(width = 20F)
    )

}

@Preview()
@Composable
fun OKayPreview() {
    RobotFaceTheme {
        Column(modifier = Modifier.wrapContentSize()) {
            RobotFace(EyeState.Angry)
        }
    }
}
