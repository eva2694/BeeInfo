package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.addHiveScreen.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import si.uni_lj.fe.mis.tobeeornottobee.R

@Composable
fun MyMap(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val singapore = LatLng(1.35, 103.87)
    var markerChecked by rememberSaveable {
        mutableStateOf(false)
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    val markerOn = ResourcesCompat.getDrawable(context.resources, R.drawable.twotone_location_on_24, null)?.toBitmap()
    val markerOff = ResourcesCompat.getDrawable(context.resources, R.drawable.twotone_location_off_24, null)?.toBitmap()




    GoogleMap(
            modifier = modifier,
            cameraPositionState = cameraPositionState
        ) {

        MarkerInfoWindow(
            state = MarkerState(position = singapore),
           
            icon = if (markerChecked)
                markerOn?.let { BitmapDescriptorFactory.fromBitmap(it) }
            else
                markerOff?.let { BitmapDescriptorFactory.fromBitmap(it) }
            ,
        ) {
            if (it.isInfoWindowShown) {
                markerChecked = !markerChecked
                it.hideInfoWindow()
            }


        }

    }

}
