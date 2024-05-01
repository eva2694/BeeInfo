package si.uni_lj.fe.mis.tobeeornottobee.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import si.uni_lj.fe.mis.tobeeornottobee.R

@Composable
fun HomeScreen(navController: NavHostController, paddingValues: PaddingValues) {


MyHiveItem()

}

@Composable
fun MyHiveItem() {
    var infoState by remember {
        mutableStateOf(false)
    }
Card(modifier = Modifier.padding(10.dp)) {


    Row(Modifier.fillMaxWidth().padding(3.dp), verticalAlignment = Alignment.Bottom) {
    Column(Modifier.weight(0.3f)) {
        AsyncImage(

            model = "https://images.unsplash.com/photo-1504392022767-a8fc0771f239?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            error = painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = "The delasign logo",
        )
        Text(text = "some name")
    }
    Column(Modifier.weight(0.7f), horizontalAlignment = Alignment.End) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "collect")
            IconButton(modifier = Modifier.weight(1f), onClick = { }) {

                Icon(
                    modifier = Modifier.fillMaxSize(), imageVector = Icons.Default.AddCircle,
                    contentDescription = ""
                )


            }

        }

        IconButton(onClick = { infoState = !infoState }) {
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
        }


    }

}



    AnimatedVisibility(visible = infoState) {
        Column {
            Text(text = "Starting: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=si.uni_lj.fe.mis.tobeeornottobee/.MainActivity }")
        }
    }



    }

    
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun oldScreen() {


    var visible by remember { mutableStateOf(true) }

    Column(Modifier.padding()) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) { // this: AnimatedVisibilityScope
            // Use AnimatedVisibilityScope#transition to add a custom animation
            // to the AnimatedVisibility.
            val background by transition.animateColor(label = "color") { state ->
                if (state == EnterExitState.Visible) Color.Blue else Color.Gray
            }
            Box(modifier = Modifier
                .size(128.dp)
                .background(background)){
                GifImage()
            }
        }
        Button(onClick = { visible = !visible}) {
            Text(text = "visible")
        }
        Button(onClick = {  }) {
            Text(text = "map screen")
        }
    }





}

@Composable
fun GifImage(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.gif_for_strt).apply(block = {

            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PrevHome() {

    HomeScreen(rememberNavController(), PaddingValues())

}