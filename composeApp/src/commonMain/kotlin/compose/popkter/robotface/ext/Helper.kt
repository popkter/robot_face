package compose.popkter.robotface.ext

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import compose.popkter.robotface.status.ITransitionProperty
import compose.popkter.robotface.status.RobotStatus


const val HEART_SAMPLE = "♥"
const val FILL_HEART_SAMPLE = "♡"
const val PENCIL_SAMPLE = "✎"
const val MIC_SAMPLE = "\uD83C\uDF99"
const val SPEECHLESS_SAMPLE = "ⲽ"
const val NOTE_SAMPLE = "\uD83D\uDCD1"
const val CLOUD_SAMPLE = "☁"
const val SUN_SAMPLE = "☀"
const val RAIN_SAMPLE = "☔"
const val COFFEE_SAMPLE = "☕"
const val MUSIC_SAMPLE = "🎹"
const val DIALOGUE_SAMPLE = "✆"
const val SPARK_SAMPLE = "✨"
const val FOOTBALL_SAMPLE = "⚽"
const val SUNGLASS_SAMPLE = "\uD83D\uDD76"

const val PIVOT_OFFSET = 0.707F

/**
 * 计算偏移值
 */
@Composable
fun ITransitionProperty.generateTransition(
    finiteTransition: Transition<RobotStatus>,
    infiniteTransition: InfiniteTransition,
) = with(this) {
    if (infinite) {
        infiniteTransition.animateFloat(
            initialValue = initialValue,
            targetValue = targetValue,
            animationSpec = animationSpec as InfiniteRepeatableSpec<Float>
        )
    } else {
        finiteTransition.animateFloat(
            transitionSpec = { animationSpec as FiniteAnimationSpec<Float> },
            targetValueByState = { targetValue }
        )
    }
}

sealed class PivotLevel {
    data object TopLeft : PivotLevel()
    data object TopCenter : PivotLevel()
    data object TopRight : PivotLevel()
    data object CenterLeft : PivotLevel()
    data object Center : PivotLevel()
    data object CenterRight : PivotLevel()
    data object BottomLeft : PivotLevel()
    data object BottomCenter : PivotLevel()
    data object BottomRight : PivotLevel()
}

/**
 * 根据PivotLevel计算旋转中心
 */
fun PivotLevel.calculateRotateCenterPivot(center: Offset, radius: Float) = when (this) {
    PivotLevel.BottomCenter -> Offset(center.x, center.y + PIVOT_OFFSET * radius)
    PivotLevel.BottomLeft -> Offset(center.x - PIVOT_OFFSET * radius, center.y + PIVOT_OFFSET * radius)
    PivotLevel.BottomRight -> Offset(center.x + PIVOT_OFFSET * radius, center.y + PIVOT_OFFSET * radius)
    PivotLevel.Center -> Offset(center.x, center.y)
    PivotLevel.CenterLeft -> Offset(center.x - PIVOT_OFFSET * radius, center.y)
    PivotLevel.CenterRight -> Offset(center.x + PIVOT_OFFSET * radius, center.y)
    PivotLevel.TopCenter -> Offset(center.x, center.y - PIVOT_OFFSET * radius)
    PivotLevel.TopLeft -> Offset(center.x - PIVOT_OFFSET * radius, center.y - PIVOT_OFFSET * radius)
    PivotLevel.TopRight -> Offset(center.x + PIVOT_OFFSET * radius, center.y - PIVOT_OFFSET * radius)
}
