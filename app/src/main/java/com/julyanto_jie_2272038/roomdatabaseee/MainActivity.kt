package com.julyanto_jie_2272038.roomdatabaseee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.julyanto_jie_2272038.roomdatabaseee.ui.screens.SimpanCatatScreen
import com.julyanto_jie_2272038.roomdatabaseee.ui.theme.RoomDatabaseeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomDatabaseeeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SimpanCatatScreen()
                }
            }
        }
    }
}