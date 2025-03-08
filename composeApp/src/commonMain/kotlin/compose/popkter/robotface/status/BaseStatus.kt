package compose.popkter.robotface.status

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.Color
import compose.popkter.robotface.ext.PivotLevel
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

sealed class RobotStatus(

    val leftEyeCornerRadius: EyeCornerRadius = EyeCornerRadius(),
    val rightEyeCornerRadius: EyeCornerRadius = EyeCornerRadius(),

    val leftEyeHorizontalRadius: EyeHorizontalRadius = EyeHorizontalRadius(),
    val rightEyeHorizontalRadius: EyeHorizontalRadius = EyeHorizontalRadius(),

    val leftTopEyeRadius: EyeVerticalRadius = EyeVerticalRadius(),
    val leftBottomEyeRadius: EyeVerticalRadius = EyeVerticalRadius(),

    val rightTopEyeRadius: EyeVerticalRadius = EyeVerticalRadius(),
    val rightBottomEyeRadius: EyeVerticalRadius = EyeVerticalRadius(),

    val rotate: EyesRotate = EyesRotate(),
    val horizontalTransition: EyesTransition = EyesTransition(),
    val verticalTransition: EyesTransition = EyesTransition(),
    val scaleX: EyesScale = EyesScale(),
    val scaleY: EyesScale = EyesScale(),

    val leftEyeRotate: EyesRotate = EyesRotate(),
    val leftEyeHorizontalTransition: EyesTransition = EyesTransition(),
    val leftEyeVerticalTransition: EyesTransition = EyesTransition(),
    val leftEyeScaleX: EyesScale = EyesScale(),
    val leftEyeScaleY: EyesScale = EyesScale(),

    val rightEyeRotate: EyesRotate = EyesRotate(),
    val rightEyeHorizontalTransition: EyesTransition = EyesTransition(),
    val rightEyeVerticalTransition: EyesTransition = EyesTransition(),
    val rightEyeScaleX: EyesScale = EyesScale(),
    val rightEyeScaleY: EyesScale = EyesScale(),

    val eyesFillColor: Color = Color.Transparent,

    val actionSample: ActionSample = ActionSample(),
    val actionRotate: ActionRotate = ActionRotate(),
    val actionScaleX: ActionScale = ActionScale(),
    val actionScaleY: ActionScale = ActionScale(),
    val actionHorizontalTransition: ActionTransition = ActionTransition(),
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
                Record
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
