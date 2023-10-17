package com.radzhabov.debtcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.radzhabov.debtcounter.ui.theme.DebtCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DebtCounterTheme {

                Text("Home Screen")
            }
        }
    }
}
