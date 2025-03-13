package compose.popkter.robotface.status

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import compose.popkter.robotface.ext.ActionSample
import compose.popkter.robotface.ext.PivotLevel
import kotlinx.serialization.Serializable

/**
 * 表情状态
 */

@Serializable
sealed class RobotStatus(

    /**
     * 左眼边缘角度
     */
    val leftEyeCornerRadius: TransitionProperty = TransitionProperty(initialValue = 30F, targetValue = 30F),

    /**
     * 右眼边缘角度
     */
    val rightEyeCornerRadius: TransitionProperty = TransitionProperty(initialValue = 30F, targetValue = 30F),

    /**
     * 左眼皮水平半径
     */
    val leftEyeHorizontalRadius: TransitionProperty = TransitionProperty(initialValue = 30F, targetValue = 30F),

    /**
     * 右眼皮水平半径
     */
    val rightEyeHorizontalRadius: TransitionProperty = TransitionProperty(initialValue = 30F, targetValue = 30F),

    /**
     * 左上眼皮垂直半径
     */
    val leftTopEyeRadius: TransitionProperty = TransitionProperty(initialValue = 60F, targetValue = 60F),

    /**
     * 左下眼皮垂直半径
     */
    val leftBottomEyeRadius: TransitionProperty = TransitionProperty(initialValue = 60F, targetValue = 60F),

    /**
     * 右上眼皮垂直半径
     */
    val rightTopEyeRadius: TransitionProperty = TransitionProperty(initialValue = 60F, targetValue = 60F),

    /**
     * 右下眼皮垂直半径
     */
    val rightBottomEyeRadius: TransitionProperty = TransitionProperty(initialValue = 60F, targetValue = 60F),

    /**
     * 眼部填充颜色
     */
    val eyesFillColor: String = "#00000000",

    /**
     * 眼部旋转角度
     */
    val eyesRotate: TransitionProperty = TransitionProperty(),

    /**
     * 眼部水平位移
     */
    val eyesHorizontalTransition: TransitionProperty = TransitionProperty(),

    /**
     * 眼部垂直位移
     */
    val eyesVerticalTransition: TransitionProperty = TransitionProperty(),

    /**
     * 眼部X方向缩放
     */
    val eyesScaleX: TransitionProperty = TransitionProperty(initialValue = 1F, targetValue = 1F),

    /**
     * 眼部Y方向缩放
     */
    val eyesScaleY: TransitionProperty = TransitionProperty(initialValue = 1F, targetValue = 1F),

    /**
     * 左眼旋转角度
     */
    val leftEyeRotate: TransitionProperty = TransitionProperty(),

    /**
     * 左眼水平位移
     */
    val leftEyeHorizontalTransition: TransitionProperty = TransitionProperty(),

    /**
     * 左眼垂直位移
     */
    val leftEyeVerticalTransition: TransitionProperty = TransitionProperty(),

    /**
     * 左眼X方向缩放
     */
    val leftEyeScaleX: TransitionProperty = TransitionProperty(initialValue = 1F, targetValue = 1F),

    /**
     * 左眼Y方向缩放
     */
    val leftEyeScaleY: TransitionProperty = TransitionProperty(initialValue = 1F, targetValue = 1F),

    /**
     * 右眼旋转角度
     */
    val rightEyeRotate: TransitionProperty = TransitionProperty(),

    /**
     * 右眼水平位移
     */
    val rightEyeHorizontalTransition: TransitionProperty = TransitionProperty(),

    /**
     * 右眼垂直位移
     */
    val rightEyeVerticalTransition: TransitionProperty = TransitionProperty(),

    /**
     * 右眼X方向缩放
     */
    val rightEyeScaleX: TransitionProperty = TransitionProperty(initialValue = 1F, targetValue = 1F),

    /**
     * 右眼Y方向缩放
     */
    val rightEyeScaleY: TransitionProperty = TransitionProperty(initialValue = 1F, targetValue = 1F),

    /**
     * 动作标识符
     */
    val actionSample: ActionSample = ActionSample(),

    /**
     * 动作标识符自身的旋转角度(center)
     */
    val actionSampleRotate: TransitionProperty = TransitionProperty(),

    /**
     * 动作标识符的旋转角度
     */
    val actionRotate: TransitionProperty = TransitionProperty(),

    /**
     * 动作标识符自身的缩放，不建议设置为可变值
     */
    val actionScale: TransitionProperty = TransitionProperty(initialValue = 1F, targetValue = 1F),

    /**
     * 动作画布X方向的缩放
     */
    val actionScaleX: TransitionProperty = TransitionProperty(initialValue = 1F, targetValue = 1F),

    /**
     * 动作画布Y方向的缩放
     */
    val actionScaleY: TransitionProperty = TransitionProperty(initialValue = 1F, targetValue = 1F),

    /**
     * 动作画布水平位移
     */
    val actionHorizontalTransition: TransitionProperty = TransitionProperty(),

    /**
     * 动作画布垂直位移
     */
    val actionVerticalTransition: TransitionProperty = TransitionProperty(),

    ) {

    companion object {
        val allStates by lazy {
            listOf(
                Ordinary,
                Blink,
                Sadness,
                Happiness,
                Music,
                Coldness,
                Speechless,
                FingerHeart,
                Angry,
                Okay,
                Talk,
                Think,
                Record,
                Rainy,
                Cloudy,
                Sunny,
                SparkLight,
                Coffee,
                Singing,
                Dialogue,
                Football,
                SunGlasses,
                TakePhoto,
                Focus
            )
        }

        fun RobotStatus.canBlinkState(): Boolean {
            return this in arrayOf(Ordinary, Blink)
        }
    }
}

@Serializable
data class TransitionProperty(
    val initialValue: Float = 0F,
    val targetValue: Float = 0F,
    val duration: Int = 200,
    val infinite: Boolean = false,
    val repeatMode: RepeatMode = RepeatMode.Restart,
    val easing: Easing = LinearEasing,
    val centerPivotLevel: PivotLevel = PivotLevel.Center,
    var animationSpec: AnimationSpec<Float>? = null
) {

    init {
        animationSpec =
            if (infinite) {
                infiniteRepeatable(
                    animation =
                        tween(
                            durationMillis = duration,
                            easing = easing
                        ),
                    repeatMode = repeatMode
                )
            } else {
                tween(
                    durationMillis = duration,
                    easing = easing
                )
            }
    }

    /**
     * 调用此方法初始化属性，否则动画生效
     */
    fun init(block: TransitionProperty.() -> Unit): TransitionProperty {
        return this.apply {
            block()
            updateAnimation()
        }
    }

    private fun updateAnimation() {
        animationSpec = if (infinite) {
            infiniteRepeatable(
                animation =
                    tween(
                        durationMillis = duration,
                        easing = easing
                    ),
                repeatMode = repeatMode
            )
        } else {
            tween(
                durationMillis = duration,
                easing = easing
            )
        }
    }
}
