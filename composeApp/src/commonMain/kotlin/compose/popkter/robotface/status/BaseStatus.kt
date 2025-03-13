package compose.popkter.robotface.status

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.Color
import compose.popkter.robotface.ext.PivotLevel

/**
 * 表情状态
 */
sealed class RobotStatus(

    /**
     * 左眼边缘角度
     */
    val leftEyeCornerRadius: EyeCornerRadius = EyeCornerRadius(),

    /**
     * 右眼边缘角度
     */
    val rightEyeCornerRadius: EyeCornerRadius = EyeCornerRadius(),

    /**
     * 左眼皮水平半径
     */
    val leftEyeHorizontalRadius: EyeHorizontalRadius = EyeHorizontalRadius(),

    /**
     * 右眼皮水平半径
     */
    val rightEyeHorizontalRadius: EyeHorizontalRadius = EyeHorizontalRadius(),

    /**
     * 左上眼皮垂直半径
     */
    val leftTopEyeRadius: EyeVerticalRadius = EyeVerticalRadius(),

    /**
     * 左下眼皮垂直半径
     */
    val leftBottomEyeRadius: EyeVerticalRadius = EyeVerticalRadius(),

    /**
     * 右上眼皮垂直半径
     */
    val rightTopEyeRadius: EyeVerticalRadius = EyeVerticalRadius(),

    /**
     * 右下眼皮垂直半径
     */
    val rightBottomEyeRadius: EyeVerticalRadius = EyeVerticalRadius(),


    /**
     * 眼部旋转角度
     */
    val eyesRotate: EyesRotate = EyesRotate(),

    /**
     * 眼部水平位移
     */
    val eyesHorizontalTransition: EyesTransition = EyesTransition(),

    /**
     * 眼部垂直位移
     */
    val eyesVerticalTransition: EyesTransition = EyesTransition(),

    /**
     * 眼部X方向缩放
     */
    val eyesScaleX: EyesScale = EyesScale(),

    /**
     * 眼部Y方向缩放
     */
    val eyesScaleY: EyesScale = EyesScale(),

    /**
     * 左眼旋转角度
     */
    val leftEyeRotate: EyesRotate = EyesRotate(),

    /**
     * 左眼水平位移
     */
    val leftEyeHorizontalTransition: EyesTransition = EyesTransition(),

    /**
     * 左眼垂直位移
     */
    val leftEyeVerticalTransition: EyesTransition = EyesTransition(),

    /**
     * 左眼X方向缩放
     */
    val leftEyeScaleX: EyesScale = EyesScale(),

    /**
     * 左眼Y方向缩放
     */
    val leftEyeScaleY: EyesScale = EyesScale(),

    /**
     * 右眼旋转角度
     */
    val rightEyeRotate: EyesRotate = EyesRotate(),

    /**
     * 右眼水平位移
     */
    val rightEyeHorizontalTransition: EyesTransition = EyesTransition(),

    /**
     * 右眼垂直位移
     */
    val rightEyeVerticalTransition: EyesTransition = EyesTransition(),

    /**
     * 右眼X方向缩放
     */
    val rightEyeScaleX: EyesScale = EyesScale(),

    /**
     * 右眼Y方向缩放
     */
    val rightEyeScaleY: EyesScale = EyesScale(),

    /**
     * 眼部填充颜色
     */
    val eyesFillColor: Color = Color.Transparent,

    /**
     * 动作标识符
     */
    val actionSample: ActionSample = ActionSample(),

    /**
     * 动作标识符自身的旋转角度(center)
     */
    val actionSampleRotate: ActionRotate = ActionRotate(),

    /**
     * 动作标识符的旋转角度
     */
    val actionRotate: ActionRotate = ActionRotate(),

    /**
     * 动作标识符自身的缩放，不建议设置为可变值
     */
    val actionScale: ActionScale = ActionScale(),

    /**
     * 动作画布X方向的缩放
     */
    val actionScaleX: ActionScale = ActionScale(),

    /**
     * 动作画布Y方向的缩放
     */
    val actionScaleY: ActionScale = ActionScale(),

    /**
     * 动作画布水平位移的缩放
     */
    val actionHorizontalTransition: ActionTransition = ActionTransition(),

    /**
     * 动作画布垂直位移的缩放
     */
    val actionVerticalTransition: ActionTransition = ActionTransition()

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
                TakePhoto
            )
        }

        fun RobotStatus.canBlinkState(): Boolean {
            return this in arrayOf(Ordinary, Blink)
        }
    }
}

data class EyesTransition(
    override var initialValue: Float = 0F,
    override var targetValue: Float = 0F
) : BaseTransitionProperty()

data class EyesScale(
    override var initialValue: Float = 1F,
    override var targetValue: Float = 1F
) : BaseTransitionProperty()

data class EyesRotate(
    override var initialValue: Float = 0F,
    override var targetValue: Float = 0F
) : BaseTransitionProperty()

data class ActionSample(val sample: String = "")

data class ActionTransition(
    override var initialValue: Float = 0F,
    override var targetValue: Float = 0F
) : BaseTransitionProperty()

data class ActionScale(
    override var initialValue: Float = 1F,
    override var targetValue: Float = 1F
) : BaseTransitionProperty()

data class ActionRotate(
    override var initialValue: Float = 0F,
    override var targetValue: Float = 0F
) : BaseTransitionProperty()


data class EyeCornerRadius(
    override var initialValue: Float = 30F,
    override var targetValue: Float = 30F
) : BaseTransitionProperty()

data class EyeHorizontalRadius(
    override var initialValue: Float = 30F,
    override var targetValue: Float = 30F
) : BaseTransitionProperty()

data class EyeVerticalRadius(
    override var initialValue: Float = 60F,
    override var targetValue: Float = 60F,
    override var duration: Int = 240
) : BaseTransitionProperty()


interface ITransitionProperty {
    val initialValue: Float
    val targetValue: Float
    val duration: Int
    val infinite: Boolean
    val repeatMode: RepeatMode
    val easing: Easing
    val animationSpec: AnimationSpec<Float>
    val centerPivotLevel: PivotLevel
}

open class BaseTransitionProperty(
    override var initialValue: Float = 0F,
    override var targetValue: Float = 0F,
    override var duration: Int = 200,
    override var infinite: Boolean = false,
    override var repeatMode: RepeatMode = RepeatMode.Restart,
    override var easing: Easing = LinearEasing,
    override var centerPivotLevel: PivotLevel = PivotLevel.Center,
    override var animationSpec: AnimationSpec<Float> =
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
) : ITransitionProperty {

    /**
     * 调用此方法初始化属性，否则动画生效
     */
    fun <T : BaseTransitionProperty> init(block: BaseTransitionProperty.() -> Unit): T {
        return this.apply {
            block()
            updateAnimation()
        } as T
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
