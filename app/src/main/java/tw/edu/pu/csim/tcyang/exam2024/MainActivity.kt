package tw.edu.pu.csim.tcyang.exam2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
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
import androidx.compose.ui.input.pointer.pointerInput
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
    var flag by remember { mutableStateOf(true) }  // 服務總覽與作者切換

    Column (modifier = Modifier
        .fillMaxSize(),
    ) {
        if (flag){
            Text(text="瑪利亞基金會服務總覽", color = Color.Blue)
        }
        else{
            Text(text="關於App作者", color = Color.Blue)
        }

        AnimatedVisibility(
            visible = flag,
            enter = fadeIn(
                initialAlpha = 0.1f,
                animationSpec = tween(durationMillis = 3000)),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 3000))
        ){
            Image(
                painterResource(id = R.drawable.service),
                contentDescription ="服務圖示"
            )
        }

        AnimatedVisibility(
            visible = !flag,
            enter = fadeIn(
                initialAlpha = 0.1f,
                animationSpec = tween(durationMillis = 3000)),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 3000))
        ){
            Image(
                painterResource(id = R.drawable.tcyang),
                contentDescription ="作者圖示"
            )
        }

        Button(
            onClick = { flag = !flag},
        ) {
            if (flag){
                Text(
                    text = "作者：資管系楊子青",
                )
            }
            else{
                Text(
                    text = "服務總覽",
                )
            }
        }

    }
}

@Composable
fun Organization(navController: NavController) {
    var flag by remember { mutableStateOf(1) }  // 愛心家園與瑪利亞學園切換
    val context = LocalContext.current  //取得App的運行環境

    Column(modifier = Modifier
        .fillMaxSize(),
    )
    {
        Text(text="主要機構", color = Color.Red)
        Row {


            Button(
                onClick = { flag = 1},
            ) {
                Text(
                    text = "台中市愛心家園",
                )
            }

            Button(
                onClick = { flag = 2},
            ) {
                Text(
                    text = "瑪利亞學園",
                )
            }
        }

        if (flag ==1){
            Text(
                text = "「台中市愛心家園」經市政府公開評選後，委託瑪利亞基金會經營管理，於91年啟用，整棟建築物有四個樓層，目前開辦就醫、就養、就學、就業四大領域的十項業務，提供身心障礙者全方位的服務。"
            )

            Text(
                text = "\n長按以下圖片，可以觀看愛心家園地圖",
                color = Color.Blue
            )

            Image(
                painterResource(id = R.drawable.lovehome),
                contentDescription ="愛心家園圖示",
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                var it = Intent(Intent.ACTION_VIEW)
                                it.data = Uri.parse("geo:0,0?q=台中市南屯區東興路一段450號")
                                context.startActivity(it)
                            }
                        )
                    }
            )
        }

        else{
            Text(
                text = "「瑪利亞學園」提供重度以及極重度多重障礙者日間照顧服務，以健康照護為基礎，支持生活多面向參與及學習概念，輔助發展重度身心障礙者自我概念為最終服務目標。"
            )

            Text(
                text = "\n雙擊以下圖片，可以觀看瑪利亞學園地圖",
                color = Color.Blue
            )

            Image(
                painterResource(id = R.drawable.campus),
                contentDescription ="瑪利亞學園圖示",
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                var it = Intent(Intent.ACTION_VIEW)
                                it.data = Uri.parse("geo:0,0?q=台中市北屯區經貿東路365號")
                                context.startActivity(it)
                            }
                        )
                    }

            )
        }
    }
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
