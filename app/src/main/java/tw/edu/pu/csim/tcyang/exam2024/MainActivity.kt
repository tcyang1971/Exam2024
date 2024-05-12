package tw.edu.pu.csim.tcyang.exam2024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tw.edu.pu.csim.tcyang.exam2024.ui.theme.Exam2024Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exam2024Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Maria()
                }
            }
        }
    }
}

@Composable
fun Introduce(navController: NavController) {
    Text( text = "簡介", color = Color.Blue)
}

@Composable
fun Organization(navController: NavController) {
    Text( text = "主要機構", color = Color.Red)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Maria() {
    val navController = rememberNavController()
    val context = LocalContext.current
    var showMenu by remember { mutableStateOf(false) }

    Column {
        TopAppBar(
            title = {
                Image(
                    painterResource(id = R.drawable.maria),
                    contentDescription ="瑪利亞基金會圖示",) },

            actions = {
                IconButton(
                    onClick = { showMenu = true }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                }

                DropdownMenu(
                    expanded = showMenu, onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("簡介") },
                        onClick = { navController.navigate("JumpIntroduce")})

                    DropdownMenuItem(
                        text = { Text("主要機構") },
                        onClick = { navController.navigate("JumpOrganization")})
                }

            }
        )

        NavHost(navController = navController, startDestination = "JumpIntroduce"){
            composable("JumpIntroduce"){
                Introduce(navController = navController)
            }
            composable("JumpOrganization"){
                Organization(navController = navController)
            }
        }
    }
}
