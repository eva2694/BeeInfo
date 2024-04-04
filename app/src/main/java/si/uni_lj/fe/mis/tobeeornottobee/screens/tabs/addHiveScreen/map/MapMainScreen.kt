package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.addHiveScreen.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun MapMainScreen(navController: NavHostController, paddingValues: PaddingValues) {
   Column(Modifier.padding(paddingValues = paddingValues)) {
       MyMap(Modifier.weight(0.5f))
       
       Column(Modifier.weight(0.5f)) {
           
       }
   }

}