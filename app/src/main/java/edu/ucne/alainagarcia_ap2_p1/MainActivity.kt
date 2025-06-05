package edu.ucne.alainagarcia_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.alainagarcia_ap2_p1.data.local.database.TareaDb
import edu.ucne.alainagarcia_ap2_p1.presentation.navigation.HostNavigation
import edu.ucne.alainagarcia_ap2_p1.ui.theme.AlainaGarcia_AP2_P1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var tareaDb : TareaDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        tareaDb = Room.databaseBuilder(
            applicationContext,
            TareaDb::class.java,
            "Tarea.db"
        ).fallbackToDestructiveMigration()
            .build()

        setContent {
            AlainaGarcia_AP2_P1Theme {
                val nav = rememberNavController()
                HostNavigation(nav)
            }
        }
    }
}