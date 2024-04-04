package si.uni_lj.fe.mis.tobeeornottobee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import si.uni_lj.fe.mis.tobeeornottobee.navigate.AppNavHost
import si.uni_lj.fe.mis.tobeeornottobee.ui.theme.ToBeeOrNotToBeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToBeeOrNotToBeeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val inAppNavigation = rememberNavController()
                    val inTabNavigation = rememberNavController()

                    AppNavHost(inAppNavigation, inTabNavigation)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToBeeOrNotToBeeTheme {
        Greeting("Android")
    }
}