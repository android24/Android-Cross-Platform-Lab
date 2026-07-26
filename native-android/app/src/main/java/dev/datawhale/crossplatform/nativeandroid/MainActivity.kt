package dev.datawhale.crossplatform.nativeandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.datawhale.crossplatform.nativeandroid.feature.cockpit.CockpitManualDemoApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CockpitManualDemoApp()
        }
    }
}
