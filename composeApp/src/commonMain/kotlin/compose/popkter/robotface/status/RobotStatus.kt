package compose.popkter.robotface.status

import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.ui.graphics.Color
import compose.popkter.robotface.ext.CLOUD_SAMPLE
import compose.popkter.robotface.ext.COFFEE_SAMPLE
import compose.popkter.robotface.ext.EMAIL_SAMPLE
import compose.popkter.robotface.ext.HEART_SAMPLE
import compose.popkter.robotface.ext.MIC_SAMPLE
import compose.popkter.robotface.ext.MUSIC_SAMPLE
import compose.popkter.robotface.ext.PENCIL_SAMPLE
import compose.popkter.robotface.ext.PivotLevel
import compose.popkter.robotface.ext.RAIN_SAMPLE
import compose.popkter.robotface.ext.SPARK_SAMPLE
import compose.popkter.robotface.ext.SPEECHLESS_SAMPLE
import compose.popkter.robotface.ext.SUN_SAMPLE


//睁眼
data object Ordinary : RobotStatus()

//眯眼
data object Blink : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 1F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 1F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 1F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 1F })

//伤心
data object Sadness : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 1F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 1F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    leftEyeRotate = EyesRotate().init { targetValue = -15F },
    rightEyeRotate = EyesRotate().init { targetValue = 15F }
)


data object Happiness : RobotStatus(

    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 0F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 0F },

    verticalTransition = EyesTransition().init {
        targetValue = 10F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    }
)


data object Music : RobotStatus(
    scaleY = EyesScale().init {
        initialValue = 1F
        targetValue = 1.05F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    rotate = EyesRotate().init {
        centerPivotLevel = PivotLevel.BottomCenter
        initialValue = -10F
        targetValue = 10F
        duration = 800
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    actionSample = ActionSample(MUSIC_SAMPLE),

    actionVerticalTransition = ActionTransition(initialValue = 110F, targetValue = 110F),

    actionScale = ActionScale().init {
        targetValue = 1.1F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    actionRotate = ActionRotate().init {
        initialValue = -10F
        targetValue = 10F
        infinite = true
        duration = 800
        repeatMode = RepeatMode.Reverse
        centerPivotLevel = PivotLevel.TopCenter
    }
)

data object Coldness : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 15F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 15F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 15F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 15F },

    verticalTransition = EyesTransition().init {
        targetValue = 40F
        duration = 300
        easing = LinearEasing
    },

    horizontalTransition = EyesTransition(
        initialValue = 0F,
        targetValue = 0F,
    ).init<EyesTransition> {
        duration = 1500
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = LinearEasing
    }.apply {
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
    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 20F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 20F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 20F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 20F },

    horizontalTransition = EyesTransition().init {
        initialValue = 0F
        targetValue = 20F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    leftEyeHorizontalTransition = EyesTransition().init {
        initialValue = 0F
        targetValue = 30F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    leftEyeVerticalTransition = EyesTransition().init {
        initialValue = 0F
        targetValue = 50F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    rightEyeHorizontalTransition = EyesTransition().init {
        initialValue = 0F
        targetValue = 30F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    rightEyeVerticalTransition = EyesTransition().init {
        initialValue = 0F
        targetValue = 50F
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },

    actionSample = ActionSample(SPEECHLESS_SAMPLE),

    actionHorizontalTransition = ActionTransition().init {
        targetValue = -150F
        infinite = false
        duration = 0
    },
    actionVerticalTransition = ActionTransition().init {
        initialValue = -200F
        targetValue = -100F
        infinite = true
        duration = 1600
        repeatMode = RepeatMode.Restart
        easing = EaseOutExpo
    },
)

data object FingerHeart : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 0F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 0F },

    rotate = EyesRotate().init {
        centerPivotLevel = PivotLevel.BottomCenter
        initialValue = -10F
        targetValue = 10F
        duration = 800
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    actionSample = ActionSample(HEART_SAMPLE),
    actionRotate = ActionRotate().init {
        centerPivotLevel = PivotLevel.Center
        initialValue = -10F
        targetValue = 10F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 800
    },

    actionVerticalTransition = ActionTransition(initialValue = 100F, targetValue = 100F)

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

    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 10F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 60F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 10F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 60F },

    verticalTransition = EyesTransition().init {
        targetValue = 10F
        duration = 600
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    leftEyeRotate = EyesRotate().init {
        targetValue = 15F
        duration = 300
        easing = EaseIn
    },

    rightEyeRotate = EyesRotate().init {
        targetValue = -15F
        duration = 300
        easing = EaseIn
    },

    eyesFillColor = Color.Red
)

data object Okay : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 0F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 40F },

    rotate = EyesRotate().init {
        initialValue = -5F
        targetValue = 5F
        duration = 800
        infinite = true
        repeatMode = RepeatMode.Reverse

    }
)

data object Talk : RobotStatus(
    scaleY = EyesScale().init {
        initialValue = 1F
        targetValue = 1.15F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    }
)

data object Think : RobotStatus(

    leftEyeScaleX = EyesScale().init {
        initialValue = 1F
        targetValue = 1.15F
        duration = 1000
        infinite = false
        repeatMode = RepeatMode.Reverse
    },

    leftEyeScaleY = EyesScale().init {
        initialValue = 1F
        targetValue = 1.15F
        duration = 1000
        infinite = false
        repeatMode = RepeatMode.Reverse
    },

    rightEyeScaleY = EyesScale().init {
        initialValue = 1F
        targetValue = 0.95F
        duration = 500
        infinite = false
        repeatMode = RepeatMode.Reverse
    },

    horizontalTransition = EyesTransition().init {
        initialValue = -20F
        targetValue = 20F
        duration = 1000
        infinite = false
        repeatMode = RepeatMode.Reverse
    }

)

data object Record : RobotStatus(
    scaleY = EyesScale().init {
        initialValue = 1F
        targetValue = 1.15F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    actionSample = ActionSample(PENCIL_SAMPLE),

    actionVerticalTransition = ActionTransition().init {
        initialValue = 90F
        targetValue = 100F
        repeatMode = RepeatMode.Reverse
        infinite = true
        duration = 200
    },
    actionHorizontalTransition = ActionTransition().init {
        initialValue = 100F
        targetValue = -100F
        repeatMode = RepeatMode.Restart
        infinite = true
        duration = 3200
    }
)

data object Rainy : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 15F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 15F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 15F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 15F },

    verticalTransition = EyesTransition().init {
        targetValue = 40F
        duration = 300
    },

    rotate = EyesRotate().init {
        initialValue = -5F
        targetValue = 5F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
    },

    actionSample = ActionSample(RAIN_SAMPLE),

    actionScale = ActionScale().init {
        infinite = false
        targetValue = 1.5F
        duration = 200
    },

    actionVerticalTransition = ActionTransition().init {
        targetValue = -120F
        infinite = false
        duration = 200
    },

    actionRotate = ActionRotate().init {
        initialValue = -15F
        targetValue = 15F
        infinite = true
        duration = 1200
        repeatMode = RepeatMode.Reverse
        centerPivotLevel = PivotLevel.BottomCenter
    }
)

data object Cloudy : RobotStatus(
    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 15F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 15F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 15F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 15F },

    verticalTransition = EyesTransition().init {
        targetValue = 40F
        duration = 300
    },

    rotate = EyesRotate().init {
        initialValue = -5F
        targetValue = 5F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
    },

    actionSample = ActionSample(CLOUD_SAMPLE),

    actionScale = ActionScale().init {
        infinite = false
        targetValue = 1.5F
        duration = 200
    },

    actionVerticalTransition = ActionTransition().init {
        targetValue = -120F
        infinite = false
        duration = 200
    },

    actionHorizontalTransition = ActionTransition(initialValue = -100F).init {
        initialValue = -100F
        targetValue = 100F
        duration = 1200
        infinite = true
        repeatMode = RepeatMode.Reverse
    }

)

data object Sunny : RobotStatus(

    leftTopEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    leftBottomEyeRadius = EyeVerticalRadius().init { targetValue = 0F },
    rightTopEyeRadius = EyeVerticalRadius().init { targetValue = 40F },
    rightBottomEyeRadius = EyeVerticalRadius().init { targetValue = 0F },

    verticalTransition = EyesTransition(initialValue = 40F).init {
        initialValue = 40F
        targetValue = 60F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    actionSample = ActionSample(SUN_SAMPLE),

    actionVerticalTransition = ActionTransition(initialValue = -120F).init {
        targetValue = -120F
        infinite = false
        duration = 200
    },

    actionScale = ActionScale(initialValue = 1.5F, targetValue = 1.5F),

    actionRotate = ActionRotate(targetValue = 180F).init {
        infinite = true
        duration = 1200
        repeatMode = RepeatMode.Reverse
    }
)

data object SparkLight : RobotStatus(

    leftTopEyeRadius = EyeVerticalRadius().init {
        initialValue = 40F
        targetValue = 1F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
        easing = EaseIn
    },
    leftBottomEyeRadius = EyeVerticalRadius().init {
        initialValue = 40F
        targetValue = 1F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
        easing = EaseIn
    },
    rightTopEyeRadius = EyeVerticalRadius().init {
        initialValue = 40F
        targetValue = 1F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
        easing = EaseIn
    },
    rightBottomEyeRadius = EyeVerticalRadius().init {
        initialValue = 40F
        targetValue = 1F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
        easing = EaseIn
    },

    verticalTransition = EyesTransition().init {
        targetValue = 40F
        duration = 300
    },

    actionSample = ActionSample(SPARK_SAMPLE),

    actionRotate = ActionRotate().init {
        initialValue = -20F
        targetValue = 20F
        centerPivotLevel = PivotLevel.BottomCenter
        duration = 1600
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    actionVerticalTransition = ActionTransition().init {
        targetValue = -120F
        infinite = false
        duration = 200
    },

    actionHorizontalTransition = ActionTransition().init {
        targetValue = 80F
        infinite = false
        duration = 200
    },

    actionScale = ActionScale().init {
        initialValue = 0.1F
        targetValue = 0.7F
        infinite = true
        duration = 800
        repeatMode = RepeatMode.Reverse
        easing = EaseInCubic
    },

)


data object Coffee : RobotStatus(

    leftTopEyeRadius = EyeVerticalRadius().init {
        initialValue = 40F
        targetValue = 0F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
        easing = EaseIn
    },
    leftBottomEyeRadius = EyeVerticalRadius().init {
        initialValue = 40F
        targetValue = 30F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
        easing = EaseIn
    },
    rightTopEyeRadius = EyeVerticalRadius().init {
        initialValue = 40F
        targetValue = 0F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
        easing = EaseIn
    },
    rightBottomEyeRadius = EyeVerticalRadius().init {
        initialValue = 40F
        targetValue = 30F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
        easing = EaseIn
    },

    actionSample = ActionSample(COFFEE_SAMPLE),

    actionRotate = ActionRotate().init {
        centerPivotLevel = PivotLevel.Center
        initialValue = -5F
        targetValue = 5F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 1200
    },

    actionVerticalTransition = ActionTransition(initialValue = 100F, targetValue = 100F)
)

data object Singing : RobotStatus(

    scaleY = EyesScale().init {
        initialValue = 1F
        targetValue = 1.05F
        duration = 400
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    verticalTransition = EyesTransition().init {
        targetValue = 20F
        duration = 800
        infinite = true
        repeatMode = RepeatMode.Reverse
    },

    actionSample = ActionSample(MIC_SAMPLE),

    actionRotate = ActionRotate().init {
        centerPivotLevel = PivotLevel.Center
        initialValue = -45F
        targetValue = -35F
        infinite = true
        repeatMode = RepeatMode.Reverse
        duration = 800
    },

    actionVerticalTransition = ActionTransition(initialValue = 100F, targetValue = 100F),

    actionHorizontalTransition = ActionTransition(initialValue = 80F, targetValue = 80F)
)

data object Dialogue : RobotStatus(
    horizontalTransition = EyesTransition(
        initialValue = 0F,
        targetValue = 0F,
    ).init<EyesTransition> {
        duration = 1500
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = LinearEasing
    }.apply {
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

    actionSample = ActionSample(EMAIL_SAMPLE),

    actionHorizontalTransition = ActionTransition(
        initialValue = 0F,
        targetValue = 0F,
    ).init<ActionTransition> {
        duration = 1500
        infinite = true
        repeatMode = RepeatMode.Restart
        easing = LinearEasing
    }.apply {
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

    actionVerticalTransition = ActionTransition(initialValue = 100F, targetValue = 100F)
)
