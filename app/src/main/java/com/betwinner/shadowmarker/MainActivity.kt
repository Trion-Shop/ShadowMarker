package com.betwinner.shadowmarker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.betwinner.shadowmarker.core.theme.ShadowMarkerTheme
import com.betwinner.shadowmarker.presentation.ui.screens.ShadowMarkerScreen
import com.betwinner.shadowmarker.presentation.viewmodel.ShadowViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ShadowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShadowMarkerTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeDrawingPadding()
                ) {
                    ShadowMarkerScreen(viewModel = viewModel)
                }
            }
        }
    }
}
