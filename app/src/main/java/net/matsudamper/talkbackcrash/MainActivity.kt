package net.matsudamper.talkbackcrash

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "/1") {
                composable("/1") {
                    Column {
                        Text(text = "1")
                        AndroidView(
                            factory = { context ->
                                EditText(context).also { view ->
                                    view.requestFocus()
                                }
                            }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Button(onClick = {
                            navController.navigate("/2")
                        }) {
                            Text(text = "1->2")
                        }
                    }
                }
                composable("/2") {
                    Column {
                        Text(text = "2")
                        Text(text = "Crash")
                    }
                }
            }
        }
    }
}
