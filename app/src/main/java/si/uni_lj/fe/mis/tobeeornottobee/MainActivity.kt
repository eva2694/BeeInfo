package si.uni_lj.fe.mis.tobeeornottobee

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import si.uni_lj.fe.mis.tobeeornottobee.data.retrofit.ClientData
import si.uni_lj.fe.mis.tobeeornottobee.data.retrofit.RetrofitInstance
import si.uni_lj.fe.mis.tobeeornottobee.navigate.AppNavHost
import si.uni_lj.fe.mis.tobeeornottobee.ui.theme.ToBeeOrNotToBeeTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var pLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toast("kd")
        kotlinx.coroutines.GlobalScope.launch(Dispatchers.Main) {
            try {
                // Fetch data from the API
                val clientData = RetrofitInstance.api.getClientData()
                // Handle the fetched data
                handleClientData(clientData)
            } catch (e: Exception) {
                // Handle exceptions, such as network errors
                e.printStackTrace()
            }        }

       val sharedPreferences = getSharedPreferences(getString(R.string.login_time_sharedPreference),Context.MODE_PRIVATE)
            val savedTime = sharedPreferences.getLong("time", Long.MAX_VALUE)
            val currentTime = System.currentTimeMillis()
        if (currentTime<savedTime)
            sharedPreferences.edit().putLong("time", currentTime).apply()

        checkPermissions()
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

    private fun handleClientData(clientData: ClientData) {
        toast(clientData.toString())
    }




    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun checkPermissions(){
        if (!checkBtPermission()){
            registerPermissionListener()
            launchBtPermissions()
        }
    }

    private fun launchBtPermissions(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.S){
            pLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA

                )
            )
        }else{
            pLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA

                )
            )
        }

    }

    private fun registerPermissionListener(){
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){

        }


    }
    fun checkBtPermission(): Boolean{
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED


        }else{
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED

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