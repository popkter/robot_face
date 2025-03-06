package compose.popkter.robotface.ui

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import compose.popkter.robotface.status.Angry
import compose.popkter.robotface.status.Blink
import compose.popkter.robotface.status.Coldness
import compose.popkter.robotface.status.FingerHeart
import compose.popkter.robotface.status.Happiness
import compose.popkter.robotface.status.Music
import compose.popkter.robotface.status.Okay
import compose.popkter.robotface.status.Ordinary
import compose.popkter.robotface.status.Record
import compose.popkter.robotface.status.RobotStatus
import compose.popkter.robotface.status.Sadness
import compose.popkter.robotface.status.Speechless
import compose.popkter.robotface.status.Talk
import compose.popkter.robotface.status.Think

@Composable
fun drawAction(
    modifier: Modifier,
    status: RobotStatus,
    rotateAngle: Float = 0F,
    scaleX: Float = 1F,
    scaleY: Float = 1F,
    horizontalTranslation: Float = 0F,
    verticalTranslation: Float = 0F,
    infiniteTransition: InfiniteTransition,
    textMeasurer: TextMeasurer
) {

    val mic = ""
    val pencil = "✎"
    val note = "\uD83D\uDCD1"
    val okay = "\uD83D\uDC4C"
    val u = "\uD83E\uDEF5"
    val dogNoise = "ᴥ"
    val cloud = "☁"
    val sun = "☀"
    val rain = "☔"
    val coffee = "☕"
    val email = "✉"
    val heart = "♡"
    val heart2 = "♥"

    val icon = when (status) {
        Angry -> ""
        Blink -> ""
        Coldness -> ""
        FingerHeart -> heart2
        Happiness -> ""
        Music -> mic
        Okay -> okay
        Ordinary -> ""
        Record -> note
        Sadness -> ""
        Speechless -> ""
        Talk -> ""
        Think -> ""
    }

    if (icon.isEmpty()) return

    Canvas(
        modifier = modifier
    ) {
        rotate(degrees = rotateAngle, pivot = center) {

            val textStyle = TextStyle(
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = FontFamily.Monospace
            )

            val textLayoutResult = textMeasurer.measure(
                text = icon,
                style = textStyle
            )

            drawText(
                textLayoutResult = textLayoutResult,
                topLeft = Offset(
                    center.x - textLayoutResult.size.width / 2,
                    center.y + textLayoutResult.size.height * 2 / 3
                )
            )
        }
    }

}
