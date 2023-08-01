package ro.nexttech.journalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import ro.nexttech.journalapp.ui.navigation.NavGraph
import ro.nexttech.journalapp.ui.theme.JournalAppTheme
import ro.nexttech.journalapp.viewmodel.GratitudeMessageViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JournalAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val gratitudeMessageViewModel: GratitudeMessageViewModel = viewModel()
                    NavGraph(gratitudeMessageViewModel = gratitudeMessageViewModel)
                }
            }
        }
    }
}