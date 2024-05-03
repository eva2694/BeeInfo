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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import si.uni_lj.fe.mis.tobeeornottobee.MainViewModel
import si.uni_lj.fe.mis.tobeeornottobee.R
import si.uni_lj.fe.mis.tobeeornottobee.navigate.TabNavRote
import si.uni_lj.fe.mis.tobeeornottobee.screens.items.MyHiveItem

@Composable
fun HomeScreen(navController: NavHostController,
               paddingValues: PaddingValues,
                viewModel: MainViewModel
) {

val myHives = viewModel.allHives.observeAsState().value?.filter { it.isCollect }

    LazyColumn {

        items(myHives?: listOf(),){
            MyHiveItem(
                isMyHiveScreen = true,
                hive = it,
                onAddClick = { viewModel.updateHive(it.copy(isCollect = true)) })
            {
                viewModel.currentHive.value = it
                navController.navigate(TabNavRote.HiveScreen.rote)


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

    //HomeScreen(rememberNavController(), PaddingValues())

}