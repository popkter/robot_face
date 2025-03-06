package compose.popkter.robotface.status

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.Color

sealed class RobotStatus(

    val eyesSizeDetail: EyesSizeDetail = EyesSizeDetail(),

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

    val fillColor: Color = Color.Transparent

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

data class EyesSizeDetail(
    val leftEyeCornerRadius: Float = 30F,
    val rightEyeCornerRadius: Float = 30F,
    val leftEyeHorizontalRadius: Float = 30F,
    val rightEyeHorizontalRadius: Float = 30F,
    val leftUpperEyelidRadius: Float = 60F,
    val leftLowerEyelidRadius: Float = 60F,
    val rightUpperEyelidRadius: Float = 60F,
    val rightLowerEyelidRadius: Float = 60F,
)

data class EyesTransition(
    private val baseProperty: ITransitionProperty = BaseTransitionProperty()
) : ITransitionProperty by baseProperty

data class EyesScale(
    private val baseProperty: BaseTransitionProperty = BaseTransitionProperty(initialValue = 1F, targetValue = 1F)
) : ITransitionProperty by baseProperty

class EyesRotate(
    val useOffsetPivot: Boolean = false,
    private val baseProperty: BaseTransitionProperty = BaseTransitionProperty()
) : ITransitionProperty by baseProperty

interface ITransitionProperty {
    val initialValue: Float
    val targetValue: Float
    val duration: Int
    val infinite: Boolean
    val repeatMode: RepeatMode
    val easing: Easing
    val animationSpec: AnimationSpec<Float>
}

data class BaseTransitionProperty(
    override val initialValue: Float = 0F,
    override val targetValue: Float = 0F,
    override val duration: Int = 200,
    override val infinite: Boolean = false,
    override val repeatMode: RepeatMode = RepeatMode.Restart,
    override val easing: Easing = LinearEasing,
    override val animationSpec: AnimationSpec<Float> =
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
) : ITransitionProperty


