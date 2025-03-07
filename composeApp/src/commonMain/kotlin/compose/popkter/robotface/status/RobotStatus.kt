package compose.popkter.robotface.status

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.ui.graphics.Color
import compose.popkter.robotface.ext.HEART_SAMPLE
import compose.popkter.robotface.ext.MUSIC_SAMPLE
import compose.popkter.robotface.ext.PENCIL_SAMPLE
import compose.popkter.robotface.ext.PivotLevel
import compose.popkter.robotface.ext.SPEECHLESS_SAMPLE


//睁眼
data object Ordinary : RobotStatus()

//眯眼
data object Blink : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().apply { targetValue = 1F },
    leftBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 1F },
    rightTopEyeRadius = EyeVerticalRadius().apply { targetValue = 1F },
    rightBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 1F })

//伤心
data object Sadness : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().apply { targetValue = 1F },
    leftBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 40F },
    rightTopEyeRadius = EyeVerticalRadius().apply { targetValue = 1F },
    rightBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 40F },
    leftEyeRotate = EyesRotate().apply { targetValue = -15F },
    rightEyeRotate = EyesRotate().apply { targetValue = -15F }
)


data object Happiness : RobotStatus(

    leftTopEyeRadius = EyeVerticalRadius().apply { targetValue = 40F },
    leftBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 0F },
    rightTopEyeRadius = EyeVerticalRadius().apply { targetValue = 40F },
    rightBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 0F },

    verticalTransition = EyesTransition().apply {
        targetValue = 10F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    })


data object Music : RobotStatus(
    rotate = EyesRotate().apply {
        centerPivotLevel = PivotLevel.BottomCenter
        initialValue = -10F
        targetValue = 10F
        duration = 1000
        infinite = true
        repeatMode = RepeatMode.Reverse

    },

    actionSample = ActionSample(MUSIC_SAMPLE), actionRotate = ActionRotate().apply {
        centerPivotLevel = PivotLevel.TopCenter
        initialValue = -10F
        targetValue = 10F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1000
    })

data object Coldness : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().apply { targetValue = 15F },
    leftBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 15F },
    rightTopEyeRadius = EyeVerticalRadius().apply { targetValue = 15F },
    rightBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 15F },

    verticalTransition = EyesTransition().apply {
        targetValue = 40F
        duration = 300
        easing = LinearEasing
    },

    horizontalTransition = EyesTransition(
        initialValue = 0F,
        targetValue = 0F,
    ).apply {
        duration = 1500
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = LinearEasing
        animationSpec = infiniteRepeatable(
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

            }, repeatMode = repeatMode
        )
    },

    eyesFillColor = Color.Cyan.copy(alpha = .8F)
)

data object Speechless : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().apply { targetValue = 20F },
    leftBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 20F },
    rightTopEyeRadius = EyeVerticalRadius().apply { targetValue = 20F },
    rightBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 20F },

    horizontalTransition = EyesTransition().apply {
        initialValue = 0F
        targetValue = 20F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    leftEyeHorizontalTransition = EyesTransition().apply {
        initialValue = 0F
        targetValue = 30F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    leftEyeVerticalTransition = EyesTransition().apply {
        initialValue = 0F
        targetValue = 50F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    rightEyeHorizontalTransition = EyesTransition().apply {
        initialValue = 0F
        targetValue = 30F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    rightEyeVerticalTransition = EyesTransition().apply {
        initialValue = 0F
        targetValue = 50F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    actionSample = ActionSample(SPEECHLESS_SAMPLE),

    actionHorizontalTransition = ActionTransition().apply {
        targetValue = -150F
        infinite = false
        duration = 0
    },
    actionVerticalTransition = ActionTransition().apply {
        initialValue = -200F
        targetValue = -100F
        infinite = true
        duration = 1600
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },
)

data object FingerHeart : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().apply { targetValue = 40F },
    leftBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 0F },
    rightTopEyeRadius = EyeVerticalRadius().apply { targetValue = 40F },
    rightBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 0F },

    rotate = EyesRotate().apply {
        centerPivotLevel = PivotLevel.BottomCenter
        initialValue = -10F
        targetValue = 10F
        duration = 800
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    actionSample = ActionSample(HEART_SAMPLE),
    actionRotate = ActionRotate().apply {
        centerPivotLevel = PivotLevel.Center
        initialValue = -10F
        targetValue = 10F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 800
    },

    actionVerticalTransition = ActionTransition().apply {
        targetValue = -50F
        infinite = false
        duration = 400
    }
)

data object Angry : RobotStatus(

    leftEyeCornerRadius = EyeCornerRadius(
        targetValue = 60F
    ),

    rightEyeCornerRadius = EyeCornerRadius(
        targetValue = 60F
    ),

    leftEyeHorizontalRadius = EyeHorizontalRadius(
        targetValue = 60F
    ),

    rightEyeHorizontalRadius = EyeHorizontalRadius(
        targetValue = 60F
    ),

    leftTopEyeRadius = EyeVerticalRadius().apply { targetValue = 10F },
    leftBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 60F },
    rightTopEyeRadius = EyeVerticalRadius().apply { targetValue = 10F },
    rightBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 60F },

    verticalTransition = EyesTransition().apply {
        targetValue = 10F
        duration = 600
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    leftEyeRotate = EyesRotate().apply {
        targetValue = 15F
        duration = 300
        easing = EaseIn
    },

    rightEyeRotate = EyesRotate().apply {
        targetValue = -15F
        duration = 300
        easing = EaseIn
    },

    eyesFillColor = Color.Red
)

data object Okay : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().apply { targetValue = 40F },
    leftBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 0F },
    rightTopEyeRadius = EyeVerticalRadius().apply { targetValue = 40F },
    rightBottomEyeRadius = EyeVerticalRadius().apply { targetValue = 40F },

    rotate = EyesRotate().apply {
        initialValue = -5F
        targetValue = 5F
        duration = 800
        infinite = true
        repeatMode = RepeatMode.Reverse

    }
)

data object Talk : RobotStatus(
    scaleY = EyesScale().apply {
        initialValue = 1F
        targetValue = 1.15F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    }
)

data object Think : RobotStatus(

    leftEyeScaleX = EyesScale().apply {
        initialValue = 1F
        targetValue = 1.15F
        duration = 1000
        infinite = false
        repeatMode = RepeatMode.Reverse
    },

    leftEyeScaleY = EyesScale().apply {
        initialValue = 1F
        targetValue = 1.15F
        duration = 1000
        infinite = false
        repeatMode = RepeatMode.Reverse
    },

    rightEyeScaleY = EyesScale().apply {
        initialValue = 1F
        targetValue = 0.95F
        duration = 500
        infinite = false
        repeatMode = RepeatMode.Reverse
    },

    horizontalTransition = EyesTransition().apply {
        initialValue = -20F
        targetValue = 20F
        duration = 1000
        infinite = false
        repeatMode = RepeatMode.Reverse
    }

)

data object Record : RobotStatus(
    scaleY = EyesScale().apply {
        initialValue = 1F
        targetValue = 1.15F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    actionSample = ActionSample(PENCIL_SAMPLE),
    actionVerticalTransition = ActionTransition().apply {
        initialValue = -50F
        targetValue = -40F
        repeatMode = RepeatMode.Reverse
        infinite = true
        duration = 200
    },
    actionHorizontalTransition = ActionTransition().apply {
        initialValue = 100F
        targetValue = -100F
        repeatMode = RepeatMode.Restart
        infinite = true
        duration = 3200
    }
)