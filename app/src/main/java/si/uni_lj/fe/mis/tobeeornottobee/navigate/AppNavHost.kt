package si.uni_lj.fe.mis.tobeeornottobee.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import si.uni_lj.fe.mis.tobeeornottobee.screens.StartScreen

sealed class AppNavRote(val rote: String){

object StartScreen: AppNavRote("start_screen")
    object FunFacts: AppNavRote("start_screen")


}

@Composable
fun AppNavHost(inAppNavigation: NavHostController, inTabNavigation: NavHostController) {


    NavHost(navController = inAppNavigation, startDestination = AppNavRote.StartScreen.rote) {
        composable(AppNavRote.StartScreen.rote) {
            StartScreen(inTabNavigation)
        }
    }
    
}