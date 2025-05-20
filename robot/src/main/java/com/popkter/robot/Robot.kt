package com.popkter.robot

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.rememberTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.popkter.robot.ui.DrawAction
import com.popkter.robot.ui.DrawEyes
import com.popkter.robot.viewmodel.RobotViewModel
import kotlinx.coroutines.flow.collectLatest


@Preview
@Composable
fun RobotPreview() {
    Robot(RobotViewModel())
}

@Composable
fun Robot(
    viewModel: RobotViewModel
) {

    val robotStatus by viewModel.robotStatus.collectAsStateWithLifecycle()

    val eyeTransitionState = remember { MutableTransitionState(robotStatus) }

    var statusRound by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        snapshotFlow { robotStatus }.collectLatest {
            statusRound = 0
            eyeTransitionState.targetState = robotStatus
            viewModel.updateRound(it)
        }
    }

    val faceFillColor = remember {
        Brush.sweepGradient(
            colors = listOf(Color.Blue, Color.Red, Color.White, Color.Blue),
            center = Offset(x = 20F, y = 20F)
        )
    }

    val finiteTransition = rememberTransition(transitionState = eyeTransitionState, label = "finiteTransition")

    val textMeasurer = rememberTextMeasurer()

    Box(
        Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(50))
            .background(
                brush = faceFillColor
            )
    ) {
        with(eyeTransitionState.targetState) {
            DrawEyes(
                modifier = Modifier.matchParentSize(),
                finiteTransition = finiteTransition,
            )

            DrawAction(
                modifier = Modifier.matchParentSize(),
                finiteTransition = finiteTransition,
                textMeasurer = textMeasurer
            )
        }
    }
}


