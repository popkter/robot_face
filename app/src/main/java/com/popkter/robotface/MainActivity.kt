package com.popkter.robotface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.popkter.robot.RobotMenu
import com.popkter.robot.viewmodel.RobotViewModel
import com.popkter.robotface.ui.theme.RobotFaceTheme


class MainActivity : ComponentActivity() {
    private val robotViewModel by viewModels<RobotViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //hide system ui
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.hide(WindowInsetsCompat.Type.statusBars())
        insetsController.hide(WindowInsetsCompat.Type.navigationBars())


        setContent {
            RobotFaceTheme {
                RobotMenu(robotViewModel)
            }
        }
    }
}
