package compose.popkter.robotface.ext

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.ui.graphics.Color

sealed class RobotStatus(

    val leftEyeHorizontalRadius: Float = 30F,
    val rightEyeHorizontalRadius: Float = 30F,

    val leftUpperEyelidRadius: Float = 60F,
    val leftLowerEyelidRadius: Float = 60F,
    val rightUpperEyelidRadius: Float = 60F,
    val rightLowerEyelidRadius: Float = 60F,

    val startAngle: Float = 0F,
    val endAngle: Float = 0F,
    val useOffsetPivot: Boolean = false,
    val rotateDuration: Int = 200,
    val rotateInfinite: Boolean = false,
    val rotateRepeatMode: RepeatMode = RepeatMode.Restart,
    val rotateEasing: Easing = LinearEasing,

    val startHorizontalTranslation: Float = 0F,
    val endHorizontalTranslation: Float = 0F,
    val horizontalTranslationDuration: Int = 200,
    val horizontalTranslationInfinite: Boolean = false,
    val horizontalTranslationRepeatMode: RepeatMode = RepeatMode.Restart,
    val horizontalEasing: Easing = LinearEasing,

    val startVerticalTranslation: Float = 0F,
    val endVerticalTranslation: Float = 0F,
    val verticalTranslationDuration: Int = 200,
    val verticalTranslationInfinite: Boolean = false,
    val verticalTranslationRepeatMode: RepeatMode = RepeatMode.Restart,
    val verticalEasing: Easing = LinearEasing,

    val scaleX: Float = 1F,
    val scaleXDuration: Int = 200,
    val scaleXInfinite: Boolean = false,
    val scaleXRepeatMode: RepeatMode = RepeatMode.Restart,
    val scaleXEasing: Easing = LinearEasing,

    val scaleY: Float = 1F,
    val scaleYDuration: Int = 200,
    val scaleYInfinite: Boolean = false,
    val scaleYRepeatMode: RepeatMode = RepeatMode.Restart,
    val scaleYEasing: Easing = LinearEasing,

    val startLeftEyeRotateAngle: Float = 0F,
    val endLeftEyeRotateAngle: Float = 0F,
    val leftEyeRotateDuration: Int = 200,
    val leftEyeRotateInfinite: Boolean = false,
    val leftEyeRotateRepeatMode: RepeatMode = RepeatMode.Restart,
    val leftEyeRotateEasing: Easing = LinearEasing,

    val startRightEyeRotateAngle: Float = 0F,
    val endRightEyeRotateAngle: Float = 0F,
    val rightEyeRotateDuration: Int = 200,
    val rightEyeRotateInfinite: Boolean = false,
    val rightEyeRotateRepeatMode: RepeatMode = RepeatMode.Restart,
    val rightEyeRotateEasing: Easing = LinearEasing,

    val startLeftEyeHorizontalTranslation: Float = 0F,
    val endLeftEyeHorizontalTranslation: Float = 0F,
    val leftEyeHorizontalTranslationDuration: Int = 200,
    val leftEyeHorizontalTranslationInfinite: Boolean = false,
    val leftEyeHorizontalTranslationRepeatMode: RepeatMode = RepeatMode.Restart,
    val leftEyeHorizontalEasing: Easing = LinearEasing,

    val startRightEyeHorizontalTranslation: Float = 0F,
    val endRightEyeHorizontalTranslation: Float = 0F,
    val rightEyeHorizontalTranslationDuration: Int = 200,
    val rightEyeHorizontalTranslationInfinite: Boolean = false,
    val rightEyeHorizontalTranslationRepeatMode: RepeatMode = RepeatMode.Restart,
    val rightEyeHorizontalEasing: Easing = LinearEasing,

    val startLeftEyeVerticalTranslation: Float = 0F,
    val endLeftEyeVerticalTranslation: Float = 0F,
    val leftEyeVerticalTranslationDuration: Int = 200,
    val leftEyeVerticalTranslationInfinite: Boolean = false,
    val leftEyeVerticalTranslationRepeatMode: RepeatMode = RepeatMode.Restart,
    val leftEyeVerticalEasing: Easing = LinearEasing,

    val startRightEyeVerticalTranslation: Float = 0F,
    val endRightEyeVerticalTranslation: Float = 0F,
    val rightEyeVerticalTranslationDuration: Int = 200,
    val rightEyeVerticalTranslationInfinite: Boolean = false,
    val rightEyeVerticalTranslationRepeatMode: RepeatMode = RepeatMode.Restart,
    val rightEyeVerticalEasing: Easing = LinearEasing,

    val leftEyeScaleX: Float = 1F,
    val leftEyeScaleXDuration: Int = 200,
    val leftEyeScaleXInfinite: Boolean = false,
    val leftEyeScaleXRepeatMode: RepeatMode = RepeatMode.Restart,
    val leftEyeScaleXEasing: Easing = LinearEasing,

    val rightEyeScaleX: Float = 1F,
    val rightEyeScaleXDuration: Int = 200,
    val rightEyeScaleXInfinite: Boolean = false,
    val rightEyeScaleXRepeatMode: RepeatMode = RepeatMode.Restart,
    val rightEyeScaleXEasing: Easing = LinearEasing,

    val leftEyeScaleY: Float = 1F,
    val leftEyeScaleYDuration: Int = 200,
    val leftEyeScaleYInfinite: Boolean = false,
    val leftEyeScaleYRepeatMode: RepeatMode = RepeatMode.Restart,
    val leftEyeScaleYEasing: Easing = LinearEasing,

    val rightEyeScaleY: Float = 1F,
    val rightEyeScaleYDuration: Int = 200,
    val rightEyeScaleYInfinite: Boolean = false,
    val rightEyeScaleYRepeatMode: RepeatMode = RepeatMode.Restart,
    val rightEyeScaleYEasing: Easing = LinearEasing,

    val fillColor: Color = Color.Transparent

) {

    data object Ordinary : RobotStatus()

    data object Blink : RobotStatus(
        leftUpperEyelidRadius = 1F,
        leftLowerEyelidRadius = 1F,
        rightUpperEyelidRadius = 1F,
        rightLowerEyelidRadius = 1F
    )

    data object Sadness : RobotStatus(
        leftUpperEyelidRadius = 0F,
        leftLowerEyelidRadius = 40F,
        rightUpperEyelidRadius = 0F,
        rightLowerEyelidRadius = 40F
    )

    data object Happiness : RobotStatus(
        leftUpperEyelidRadius = 40F,
        leftLowerEyelidRadius = 0F,
        rightUpperEyelidRadius = 40F,
        rightLowerEyelidRadius = 0F,

        endVerticalTranslation = 10F,
        verticalTranslationDuration = 400,
        verticalTranslationInfinite = true,
        verticalTranslationRepeatMode = RepeatMode.Reverse
    )

    data object Music : RobotStatus(
        useOffsetPivot = true,
        startAngle = -10F,
        endAngle = 10F,
        rotateDuration = 800,
        rotateInfinite = true,
        rotateRepeatMode = RepeatMode.Reverse
    )

    data object Coldness : RobotStatus(
        leftUpperEyelidRadius = 15F,
        leftLowerEyelidRadius = 15F,
        rightUpperEyelidRadius = 15F,
        rightLowerEyelidRadius = 15F,

        fillColor = Color.Cyan.copy(alpha = .8F)
    )

    data object Speechless : RobotStatus(
        leftUpperEyelidRadius = 20F,
        leftLowerEyelidRadius = 20F,
        rightUpperEyelidRadius = 20F,
        rightLowerEyelidRadius = 20F,

        endLeftEyeHorizontalTranslation = 30F,
        leftEyeHorizontalTranslationDuration = 1000,
        leftEyeHorizontalTranslationInfinite = false,
        leftEyeHorizontalEasing = EaseOut,

        endRightEyeHorizontalTranslation = 30F,
        rightEyeHorizontalTranslationDuration = 1000,
        rightEyeHorizontalTranslationInfinite = false,
        rightEyeHorizontalEasing = EaseOut,

        endLeftEyeVerticalTranslation = 30F,
        leftEyeVerticalTranslationDuration = 1000,
        leftEyeVerticalTranslationInfinite = false,
        leftEyeVerticalEasing = EaseOut,

        endRightEyeVerticalTranslation = 30F,
        rightEyeVerticalTranslationDuration = 1000,
        rightEyeVerticalTranslationInfinite = false,
        rightEyeVerticalEasing = EaseOut,

        endHorizontalTranslation = 20F,
        horizontalTranslationDuration = 400,
        horizontalTranslationInfinite = true,
        horizontalTranslationRepeatMode = RepeatMode.Reverse
    )

    data object FingerHeart : RobotStatus(
        leftUpperEyelidRadius = 40F,
        leftLowerEyelidRadius = 0F,
        rightUpperEyelidRadius = 40F,
        rightLowerEyelidRadius = 0F
    )

    data object Angry : RobotStatus(
//        leftEyeHorizontalRadius = 40F,
//        rightEyeHorizontalRadius = 40F,

        leftUpperEyelidRadius = 1F,
        leftLowerEyelidRadius = 40F,
        rightUpperEyelidRadius = 10F,
        rightLowerEyelidRadius = 40F,

        endLeftEyeRotateAngle = 15F,
        leftEyeRotateDuration = 300,
        leftEyeRotateEasing = EaseIn,

        endRightEyeRotateAngle = -15F,
        rightEyeRotateDuration = 300,
        rightEyeRotateEasing = EaseIn,

        endVerticalTranslation = 10F,
        verticalTranslationDuration = 600,
        verticalTranslationInfinite = true,
        verticalTranslationRepeatMode = RepeatMode.Reverse,

        fillColor = Color.Red
    )

    data object Okay : RobotStatus(
        leftUpperEyelidRadius = 40F,
        leftLowerEyelidRadius = 0F,
        rightUpperEyelidRadius = 40F,
        rightLowerEyelidRadius = 40F,

        startAngle = -5F,
        endAngle = 5F,
        rotateDuration = 800,
        rotateInfinite = true,
        rotateRepeatMode = RepeatMode.Reverse
    )

    data object Talk : RobotStatus(
        scaleY = 1.15F,
        scaleYDuration = 400,
        scaleYInfinite = true,
        scaleYRepeatMode = RepeatMode.Reverse
    )

    data object Think : RobotStatus(

        scaleX = 1.1F,
        scaleXDuration = 500,
        scaleXInfinite = true,
        scaleXRepeatMode = RepeatMode.Reverse,

        scaleY = 1.1F,
        scaleYDuration = 500,
        scaleYInfinite = true,
        scaleYRepeatMode = RepeatMode.Reverse,

        startHorizontalTranslation = -20F,
        endHorizontalTranslation = 20F,
        horizontalTranslationDuration = 1000,
        horizontalTranslationInfinite = true,
        horizontalTranslationRepeatMode = RepeatMode.Reverse,
    )

    companion object {
        val allStates by lazy {
            listOf(
                Ordinary, Blink, Sadness, Happiness, Music, Coldness, Speechless, FingerHeart, Angry, Okay, Talk, Think
            )
        }

        fun RobotStatus.canBlinkState(): Boolean {
            return this in arrayOf(Ordinary, Blink)
        }
    }
}