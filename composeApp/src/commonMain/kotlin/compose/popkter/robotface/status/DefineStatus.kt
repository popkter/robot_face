package compose.popkter.robotface.status

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.ui.graphics.Color


//睁眼
data object Ordinary : RobotStatus()

//眯眼
data object Blink : RobotStatus(
    eyesSizeDetail = EyesSizeDetail(
        leftUpperEyelidRadius = 1F,
        leftLowerEyelidRadius = 1F,
        rightUpperEyelidRadius = 1F,
        rightLowerEyelidRadius = 1F
    )
)

//伤心
data object Sadness : RobotStatus(
    eyesSizeDetail = EyesSizeDetail(
        leftUpperEyelidRadius = 1F,
        leftLowerEyelidRadius = 40F,
        rightUpperEyelidRadius = 1F,
        rightLowerEyelidRadius = 40F
    ),

    leftEyeRotate = EyesRotate(
        baseProperty = BaseTransitionProperty(
            targetValue = -15F
        )
    ),

    rightEyeRotate = EyesRotate(
        baseProperty = BaseTransitionProperty(
            targetValue = 15F
        )
    )
)


data object Happiness : RobotStatus(
    eyesSizeDetail = EyesSizeDetail(
        leftUpperEyelidRadius = 40F,
        leftLowerEyelidRadius = 0F,
        rightUpperEyelidRadius = 40F,
        rightLowerEyelidRadius = 0F,
    ),
    verticalTransition = EyesTransition(
        baseProperty = BaseTransitionProperty(
            targetValue = 10F,
            duration = 400,
            infinite = true,
            repeatMode = RepeatMode.Reverse
        )
    )
)

data object Music : RobotStatus(
    rotate = EyesRotate(
        useOffsetPivot = true,
        baseProperty = BaseTransitionProperty(
            initialValue = -10F,
            targetValue = 10F,
            duration = 800,
            infinite = true,
            repeatMode = RepeatMode.Reverse
        ),
    )
)

data object Coldness : RobotStatus(
    eyesSizeDetail = EyesSizeDetail(
        leftUpperEyelidRadius = 15F,
        leftLowerEyelidRadius = 15F,
        rightUpperEyelidRadius = 15F,
        rightLowerEyelidRadius = 15F
    ),

    verticalTransition = EyesTransition(
        baseProperty = BaseTransitionProperty(
            targetValue = 40F,
            duration = 300,
            easing = LinearEasing,
        )
    ),

    horizontalTransition = EyesTransition(
        baseProperty = object : ITransitionProperty {
            override val initialValue: Float = 0F
            override val targetValue: Float = 0F
            override val duration: Int = 1500
            override val infinite: Boolean = true
            override val repeatMode: RepeatMode = RepeatMode.Restart
            override val easing: Easing = LinearEasing
            override val animationSpec: AnimationSpec<Float> = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = duration
                    0f at 420

                    (-20F) at 480

                    20f at 640

                    (-20F) at 760

                    15F at 880

                    (-10F) at 980

                    10F at 1080

                    (-5F) at 1160

                    (5F) at 1240

                },
                repeatMode = repeatMode
            )
        }
    ),

    fillColor = Color.Cyan.copy(alpha = .8F)
)

data object Speechless : RobotStatus(
    eyesSizeDetail = EyesSizeDetail(
        leftUpperEyelidRadius = 20F,
        leftLowerEyelidRadius = 20F,
        rightUpperEyelidRadius = 20F,
        rightLowerEyelidRadius = 20F
    ),

    horizontalTransition = EyesTransition(
        baseProperty = BaseTransitionProperty(
            targetValue = 20F,
            duration = 400,
            infinite = true,
            repeatMode = RepeatMode.Reverse
        )
    ),

    leftEyeHorizontalTransition = EyesTransition(
        baseProperty = BaseTransitionProperty(
            targetValue = 30F,
            duration = 1000,
            infinite = false,
            easing = EaseOut
        )
    ),

    leftEyeVerticalTransition = EyesTransition(
        baseProperty = BaseTransitionProperty(
            targetValue = 30F,
            duration = 1000,
            infinite = false,
            easing = EaseOut
        )
    ),

    rightEyeHorizontalTransition = EyesTransition(
        baseProperty = BaseTransitionProperty(
            targetValue = 30F,
            duration = 1000,
            infinite = false,
            easing = EaseOut
        )
    ),

    rightEyeVerticalTransition = EyesTransition(
        baseProperty = BaseTransitionProperty(
            targetValue = 30F,
            duration = 1000,
            infinite = false,
            easing = EaseOut
        )
    )
)

data object FingerHeart : RobotStatus(
    eyesSizeDetail = EyesSizeDetail(
        leftUpperEyelidRadius = 40F,
        leftLowerEyelidRadius = 0F,
        rightUpperEyelidRadius = 40F,
        rightLowerEyelidRadius = 0F
    ),

    rotate = EyesRotate(
        useOffsetPivot = true,

        baseProperty = BaseTransitionProperty(
            initialValue = -10F,
            targetValue = 10F,
            duration = 800,
            infinite = true,
            repeatMode = RepeatMode.Reverse
        )
    )
)

data object Angry : RobotStatus(
    eyesSizeDetail = EyesSizeDetail(
        leftEyeCornerRadius = 60F,
        rightEyeCornerRadius = 60F,

        leftEyeHorizontalRadius = 60F,
        rightEyeHorizontalRadius = 60F,

        leftUpperEyelidRadius = 10F,
        leftLowerEyelidRadius = 60F,
        rightUpperEyelidRadius = 10F,
        rightLowerEyelidRadius = 60F
    ),

    verticalTransition = EyesTransition(
        baseProperty = BaseTransitionProperty(
            targetValue = 10F,
            duration = 600,
            infinite = true,
            repeatMode = RepeatMode.Reverse,
        )
    ),

    leftEyeRotate = EyesRotate(
        baseProperty = BaseTransitionProperty(
            targetValue = 15F,
            duration = 300,
            easing = EaseIn
        )
    ),

    rightEyeRotate = EyesRotate(
        baseProperty = BaseTransitionProperty(
            targetValue = -15F,
            duration = 300,
            easing = EaseIn
        )
    ),

    fillColor = Color.Red
)

data object Okay : RobotStatus(
    eyesSizeDetail = EyesSizeDetail(
        leftUpperEyelidRadius = 40F,
        leftLowerEyelidRadius = 0F,
        rightUpperEyelidRadius = 40F,
        rightLowerEyelidRadius = 40F
    ),

    rotate = EyesRotate(
        baseProperty = BaseTransitionProperty(
            initialValue = -5F, targetValue = 5F, duration = 800, infinite = true, repeatMode = RepeatMode.Reverse
        )
    )
)

data object Talk : RobotStatus(
    scaleY = EyesScale(
        baseProperty = BaseTransitionProperty(
            initialValue = 1F,
            targetValue = 1.15F,
            duration = 400,
            infinite = true,
            repeatMode = RepeatMode.Reverse
        )
    )
)

data object Think : RobotStatus(

    leftEyeScaleX = EyesScale(
        baseProperty = BaseTransitionProperty(
            initialValue = 1F,
            targetValue = 1.15F,
            duration = 1000,
            infinite = false,
            repeatMode = RepeatMode.Reverse,
        )
    ),

    leftEyeScaleY = EyesScale(
        baseProperty = BaseTransitionProperty(
            initialValue = 1F,
            targetValue = 1.15F,
            duration = 1000,
            infinite = false,
            repeatMode = RepeatMode.Reverse,
        )
    ),

    rightEyeScaleY = EyesScale(
        baseProperty = BaseTransitionProperty(
            initialValue = 1F,
            targetValue = 0.95F,
            duration = 500,
            infinite = false,
            repeatMode = RepeatMode.Reverse,
        )
    ),

    horizontalTransition = EyesTransition(
        baseProperty = BaseTransitionProperty(
            initialValue = -20F,
            targetValue = 20F,
            duration = 1000,
            infinite = false,
            repeatMode = RepeatMode.Reverse,
        )
    )

)

data object Record : RobotStatus(
    scaleY = EyesScale(
        baseProperty = BaseTransitionProperty(
            initialValue = 1F,
            targetValue = 1.15F,
            duration = 400,
            infinite = true,
            repeatMode = RepeatMode.Reverse
        )
    )
)