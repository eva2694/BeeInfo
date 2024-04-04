package si.uni_lj.fe.mis.tobeeornottobee.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import si.uni_lj.fe.mis.tobeeornottobee.navigate.TabNavHost

@Composable
fun StartScreen(inTabNavigation: NavHostController) {
  TabNavHost(inTabNavigation = inTabNavigation)

}