package com.popkter.robotface

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.popkter.robotface.EyeState.Companion.canBlinkState
import com.popkter.robotface.ui.theme.RobotFaceTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //hide system ui
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.hide(WindowInsetsCompat.Type.statusBars())
        insetsController.hide(WindowInsetsCompat.Type.navigationBars())


        setContent {

            var eyeState: EyeState by remember { mutableStateOf(EyeState.Ordinary) }

            LaunchedEffect(Unit) {
                snapshotFlow { eyeState }
                    .collect { state ->
                        while (eyeState.canBlinkState()) {
                            delay((100..3000).random().toLong())
                            if (!eyeState.canBlinkState()) break
                            eyeState = EyeState.Blink
                            delay(200)
                            if (!eyeState.canBlinkState()) break
                            eyeState = EyeState.Ordinary
                        }
                    }
            }

            RobotFaceTheme {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        RobotFace(eyeState)
                    }

                    LazyVerticalGrid(
                        modifier = Modifier.weight(1F),
                        columns = GridCells.Fixed(2)
                    ) {
                        items(items = EyeState.allStates, key = { it.name }) { state ->
                            Log.d("EyeState", "Rendering: ${state.name}")  // 检查是否为 null
                            Button(onClick = {
                                eyeState = state
                            }) {
                                Text(text = state.name)
                            }
                        }
                    }
                }

            }
        }
    }
}
