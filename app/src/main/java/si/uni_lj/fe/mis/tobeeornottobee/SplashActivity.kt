package si.uni_lj.fe.mis.tobeeornottobee

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.study.funFactScreen.FunFactsScreen
import si.uni_lj.fe.mis.tobeeornottobee.ui.theme.ToBeeOrNotToBeeTheme


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToBeeOrNotToBeeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting2("Android")
                }
            }
        }
    }
    @Composable
    fun Greeting2(name: String, modifier: Modifier = Modifier) {
        LaunchedEffect(key1 = true) {
            delay(1000)
            startActivity(Intent(this@SplashActivity,MainActivity::class.java))
        }

        FunFactsScreen()
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ToBeeOrNotToBeeTheme {
       // Greeting2("Android")
    }
}