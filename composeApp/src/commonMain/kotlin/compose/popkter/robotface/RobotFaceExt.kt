package compose.project.demo

import androidx.compose.ui.geometry.Size

/**
 * 左眼尺寸
 */
fun calculateLeftEyeSize(eyeState: EyeState): Size {
    return when (eyeState) {
        EyeState.FingerHeart,
        EyeState.PlayMusic,
        EyeState.Ordinary,
        EyeState.Smile,
        EyeState.OKay,
        EyeState.Mute,
        EyeState.Cry -> Size(60F, 100F)

        EyeState.Blink,
        EyeState.ColdSweat -> Size(60F, 1F)

        EyeState.Angry -> Size(20F,20F)
    }
}

/**
 * 右眼尺寸
 */
fun calculateRightEyeSize(eyeState: EyeState): Size {
    return when (eyeState) {
        EyeState.FingerHeart,
        EyeState.PlayMusic,
        EyeState.Ordinary,
        EyeState.Smile,
        EyeState.OKay,
        EyeState.Mute,
        EyeState.Cry -> Size(60F, 100F)

        EyeState.Blink,
        EyeState.ColdSweat -> Size(60F, 1F)

        EyeState.Angry -> Size(20F,20F)
    }
}

/**
 * 眼距
 */
fun calculateEyeSpacing(eyeState: EyeState): Float {
    return when (eyeState) {
        EyeState.FingerHeart,
        EyeState.PlayMusic,
        EyeState.Ordinary,
        EyeState.Smile,
        EyeState.OKay,
        EyeState.Cry,
        EyeState.Blink,
        EyeState.Mute,
        EyeState.Angry,
        EyeState.ColdSweat -> 50F
    }
}

/**
 * 眼睛旋转角度
 */
fun calculateEyeDegrees(eyeState: EyeState): Float {
    return when (eyeState) {
        EyeState.Ordinary,
        EyeState.Cry,
        EyeState.Blink,
        EyeState.ColdSweat,
        EyeState.FingerHeart,
        EyeState.PlayMusic,
        EyeState.Mute,
        EyeState.Angry,
        EyeState.Smile -> 0

        EyeState.OKay -> 10
    }.toFloat()
}

/**
 * 眼睛纵向偏移
 */
fun calculateEyeOffsetHeight(eyeState: EyeState): Float {
    return when (eyeState) {
        EyeState.ColdSweat,
        EyeState.OKay,
        EyeState.Ordinary,
        EyeState.Cry,
        EyeState.Blink,
        EyeState.PlayMusic,
        EyeState.FingerHeart,
        EyeState.Mute,
        EyeState.Angry,
        EyeState.Smile -> -30
    }.toFloat()
}


/**
 * 计算眼睛开度和旋转角度
 */
fun calculateEyesSweepAngle(key: EyelidPosition, eyeState: EyeState): Float {
    return when (key) {
        EyelidPosition.LeftUpEyelid -> {
            when (eyeState) {
                EyeState.ColdSweat,
                EyeState.FingerHeart,
                EyeState.PlayMusic,
                EyeState.Smile,
                EyeState.Blink,
                EyeState.OKay,
                EyeState.Mute,
                EyeState.Angry,
                EyeState.Ordinary -> -180

                EyeState.Cry -> 0
            }
        }


        EyelidPosition.LeftLowEyelid -> {
            when (eyeState) {
                EyeState.Ordinary,
                EyeState.Cry,
                EyeState.Blink,
                EyeState.Mute,
                EyeState.Angry,
                EyeState.ColdSweat -> 180

                EyeState.OKay,
                EyeState.FingerHeart,
                EyeState.PlayMusic,
                EyeState.Smile -> 0
            }
        }


        EyelidPosition.RightUpEyelid -> {
            when (eyeState) {
                EyeState.ColdSweat,
                EyeState.FingerHeart,
                EyeState.PlayMusic,
                EyeState.Smile,
                EyeState.Blink,
                EyeState.OKay,
                EyeState.Angry,
                EyeState.Mute,
                EyeState.Ordinary -> -180

                EyeState.Cry -> 0
            }
        }

        EyelidPosition.RightLowEyelid -> {
            when (eyeState) {
                EyeState.Ordinary,
                EyeState.Cry,
                EyeState.OKay,
                EyeState.Mute,
                EyeState.Blink,
                EyeState.Angry,
                EyeState.ColdSweat -> 180

                EyeState.FingerHeart -> 0
                EyeState.PlayMusic -> 0
                EyeState.Smile -> 0
            }
        }

    }.toFloat()
}


operator fun Size.minus(other: Size): Size {
    return Size(
        width - other.width,
        height - other.height
    )
}

operator fun Size.plus(other: Size): Size {
    return Size(
        width + other.width,
        height + other.height
    )
}