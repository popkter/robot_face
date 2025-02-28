package compose.popkter.robotface

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.popkter.robotface.ui.view.singleEye
import compose.popkter.robotface.ui.drawEye
import kotlin.math.max
import kotlin.math.min

@Composable
fun AppV2(isLandScape: Boolean = false) {
    var leftTopSliderIndex by remember { mutableFloatStateOf(150F) }
    var leftBottomSliderIndex by remember { mutableFloatStateOf(150F) }

    var rightTopSliderIndex by remember { mutableFloatStateOf(150F) }
    var rightBottomSliderIndex by remember { mutableFloatStateOf(150F) }

    var leftEyeCornerRadius by remember { mutableFloatStateOf(30F) }
    var rightEyeCornerRadius by remember { mutableFloatStateOf(30F) }

    var leftEyeRotatedAngle by remember { mutableFloatStateOf(0F) }
    var rightEyeRotatedAngle by remember { mutableFloatStateOf(0F) }
    var bothEyesRotatedAngle by remember { mutableFloatStateOf(0F) }

    var leftEyeHorizontalTransition by remember { mutableFloatStateOf(0F) }
    var rightEyeHorizontalTransition by remember { mutableFloatStateOf(0F) }
    var bothEyesHorizontalTransition by remember { mutableFloatStateOf(0F) }

    var leftEyeVerticalTransition by remember { mutableFloatStateOf(0F) }
    var rightEyeVerticalTransition by remember { mutableFloatStateOf(0F) }
    var bothEyesVerticalTransition by remember { mutableFloatStateOf(0F) }

    val density = LocalDensity.current

    val faceWidth = with(density) { 200.dp.toPx() }
    val leftEyeOffset = Offset(faceWidth / 3, faceWidth * 2 / 5)
    val rightEyeOffset = Offset(faceWidth * 2 / 3, faceWidth * 2 / 5)
    val eyesCenterOffset = Offset(faceWidth / 2, faceWidth * 2 / 5)
    val eyesWidth = faceWidth / 3F

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            EyeHeightWithRadius(
                modifier = Modifier.weight(1F).padding(10.dp),
                sliderIndex = leftTopSliderIndex,
                onSliderChanged = { leftTopSliderIndex = it },
                radius = leftEyeCornerRadius,
                range = 0F..(eyesWidth / 5F),
                onRadiusChanged = { leftEyeCornerRadius = it })

            EyeHeightWithRadius(
                modifier = Modifier.weight(1F).padding(10.dp),
                sliderIndex = rightTopSliderIndex,
                onSliderChanged = { rightTopSliderIndex = it },
                radius = leftEyeCornerRadius,
                range = 0F..(eyesWidth / 5F),
                onRadiusChanged = { leftEyeCornerRadius = it })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            EyeHeightWithRadius(
                modifier = Modifier.weight(1F).padding(10.dp),
                sliderIndex = leftBottomSliderIndex,
                onSliderChanged = { leftBottomSliderIndex = it },
                radius = rightEyeCornerRadius,
                range = 0F..(eyesWidth / 5F),
                onRadiusChanged = { rightEyeCornerRadius = it })

            EyeHeightWithRadius(
                modifier = Modifier.weight(1F).padding(10.dp),
                sliderIndex = rightBottomSliderIndex,
                onSliderChanged = { rightBottomSliderIndex = it },
                radius = rightEyeCornerRadius,
                range = 0F..(eyesWidth / 5F),
                onRadiusChanged = { rightEyeCornerRadius = it })
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(bottom = 250.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            EyeOffset(
                modifier = Modifier.weight(1F),
                "左眼水平偏移",
                leftEyeHorizontalTransition
            ) { leftEyeHorizontalTransition = it }
            EyeOffset(
                modifier = Modifier.weight(1F),
                "水平偏移",
                bothEyesHorizontalTransition
            ) { bothEyesHorizontalTransition = it }
            EyeOffset(
                modifier = Modifier.weight(1F),
                "右眼水平偏移",
                rightEyeHorizontalTransition
            ) { rightEyeHorizontalTransition = it }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(bottom = 200.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            EyeOffset(
                modifier = Modifier.weight(1F),
                "左眼垂直偏移",
                leftEyeVerticalTransition
            ) { leftEyeVerticalTransition = it }
            EyeOffset(modifier = Modifier.weight(1F), "垂直偏移", bothEyesVerticalTransition) {
                bothEyesVerticalTransition = it
            }
            EyeOffset(
                modifier = Modifier.weight(1F),
                "右眼垂直偏移",
                rightEyeVerticalTransition
            ) { rightEyeVerticalTransition = it }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(bottom = 150.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            EyeRotatedAngle(modifier = Modifier.weight(1F), "左眼角度", leftEyeRotatedAngle) { leftEyeRotatedAngle = it }
            EyeRotatedAngle(modifier = Modifier.weight(1F), "眼睛角度", bothEyesRotatedAngle) { bothEyesRotatedAngle = it }
            EyeRotatedAngle(modifier = Modifier.weight(1F), "右眼角度", rightEyeRotatedAngle) { rightEyeRotatedAngle = it }
        }

        Row(
            Modifier
                .padding(top = 180.dp)
                .size(200.dp, 200.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Color.Black)
                .align(Alignment.TopCenter),
        ) {

            Canvas(modifier = Modifier.fillMaxSize()) {

                translate(left = bothEyesHorizontalTransition, top = bothEyesVerticalTransition) {
                    rotate(degrees = bothEyesRotatedAngle, pivot = eyesCenterOffset) {
                        drawEye(
                            center = leftEyeOffset,
                            horizontalRadius = eyesWidth / 5,
                            upperEyelidRadius = leftTopSliderIndex / 2,
                            lowerEyelidRadius = leftBottomSliderIndex / 2,
                            cornerRadius = leftEyeCornerRadius,
                            horizontalTranslation = leftEyeHorizontalTransition,
                            verticalTranslation = leftEyeVerticalTransition,
                            rotateAngle = leftEyeRotatedAngle
                        )

                        drawEye(
                            center = rightEyeOffset,
                            horizontalRadius = eyesWidth / 5,
                            upperEyelidRadius = rightTopSliderIndex / 2,
                            lowerEyelidRadius = rightBottomSliderIndex / 2,
                            cornerRadius = rightEyeCornerRadius,
                            horizontalTranslation = rightEyeHorizontalTransition,
                            verticalTranslation = rightEyeVerticalTransition,
                            rotateAngle = rightEyeRotatedAngle
                        )
                    }
                }
            }

        }

    }
}

@Composable
fun EyeHeightWithRadius(
    modifier: Modifier,
    sliderIndex: Float,
    range: ClosedFloatingPointRange<Float> = 0F..90F,
    radius: Float,
    onSliderChanged: (Float) -> Unit,
    onRadiusChanged: (Float) -> Unit,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "当前高度：$sliderIndex")
        Slider(
            value = sliderIndex,
            onValueChange = {
                onSliderChanged(it)
            },
            valueRange = 0F..150F,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Text(text = "下方角度：$radius")
        Slider(
            value = radius,
            onValueChange = {
                onRadiusChanged(it)
            },
            valueRange = range,
            steps = 7,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}

@Composable
fun EyeOffset(
    modifier: Modifier,
    title: String,
    radius: Float,
    onRadiusChanged: (Float) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "$title：${radius.toInt()}")
        Slider(
            value = radius,
            onValueChange = {
                onRadiusChanged(it)
            },
            valueRange = -20F..20F,
            steps = 19,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}

@Composable
fun EyeRotatedAngle(
    modifier: Modifier,
    title: String,
    radius: Float,
    onRadiusChanged: (Float) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "$title：${radius.toInt()}")
        Slider(
            value = radius,
            onValueChange = {
                onRadiusChanged(it)
            },
            valueRange = -180F..180F,
            steps = 35,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}
