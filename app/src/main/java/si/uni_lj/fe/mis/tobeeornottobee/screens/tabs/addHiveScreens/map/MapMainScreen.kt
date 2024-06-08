package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.addHiveScreens.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import si.uni_lj.fe.mis.tobeeornottobee.MainViewModel
import si.uni_lj.fe.mis.tobeeornottobee.model.hives.room.HiveDbEntity
import si.uni_lj.fe.mis.tobeeornottobee.screens.items.MyHiveItem

@Composable
fun MapMainScreen(navController: NavHostController, paddingValues: PaddingValues, vm: MainViewModel) {
    if (vm.allHives.value?.isEmpty()==true)
    for ( i in 1..5)
        vm.addHive(
            HiveDbEntity(
            id = i,
            name = "hive $i",
            imageURL = "https://images.unsplash.com/photo-1504392022767-a8fc0771f239?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            latitude =1.35+i,
            longitude = 103.87+i,
            humidity = 50.9f,
            temperature = 36.6f,
            beeCount = 65000,
            isCollect = false,
            voc = 0)
        )
    val singapore = LatLng(/* latitude = */ 1.35,  /* longitude = */ 103.87)

    var selectLocation = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 2f)
    }

   Column(Modifier.padding(paddingValues = paddingValues)) {
       MyMap(Modifier.weight(0.5f), vm, selectLocation)
       
       Column(Modifier.weight(0.5f)) {
           val hives = vm.allHives.observeAsState().value



           LazyColumn() {

               items(hives?: listOf(),){
                   MyHiveItem(
                       isMyHiveScreen = true,
                       hive = it, onAddClick = { vm.updateHive(it.copy(isCollect = true)) })
                   {

                       selectLocation.position = CameraPosition.fromLatLngZoom(LatLng(it.latitude, it.longitude), 10f)



                   }
               }

           }
       }
   }

}