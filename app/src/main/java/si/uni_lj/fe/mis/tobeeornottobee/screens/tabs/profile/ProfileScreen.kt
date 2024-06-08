package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.profile

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import si.uni_lj.fe.mis.tobeeornottobee.MainViewModel
import si.uni_lj.fe.mis.tobeeornottobee.R
import si.uni_lj.fe.mis.tobeeornottobee.model.hives.room.HiveDbEntity
import si.uni_lj.fe.mis.tobeeornottobee.navigate.TabNavRote
import java.util.concurrent.TimeUnit

@Composable
fun ProfileScreen(vm: MainViewModel, inTabNavigation: NavHostController, paddingValues: PaddingValues) {

    val hives = vm.allHives.observeAsState()

    val sharedPreferences = (LocalContext.current as (Activity)).getSharedPreferences(
        stringResource(R.string.login_time_sharedPreference),
        Context.MODE_PRIVATE)
    val savedTime = sharedPreferences.getLong("time", Long.MAX_VALUE)
    val timeDifference =   System.currentTimeMillis() - savedTime

    val user= User("some",1,TimeUnit.MILLISECONDS.toDays(timeDifference.toLong()).toString(),
        achievements = listOf(
            Achievement("streak","1 week"),
            Achievement("streak","1 week"),
        Achievement("streak","1 week"),
    Achievement("streak","1 week")),
        hives = hives.value?: listOf()



    )
    Log.d("Time", savedTime.toString())
    Log.d("Time", System.currentTimeMillis().toString())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 0.dp)
            .padding(paddingValues)
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var imageUri by remember { mutableStateOf<Uri?>(null) }

        ProfilePicture(user.name,onImageSelected = { uri ->
            imageUri = uri
            // Do something with the selected image URI if needed
        })
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "name: ${user.level}",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "My Achievements:",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        AchievementsList(user.achievements,user.streak.toString())
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "My boxes:",
            style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        HivesGrid(user.hives, inTabNavigation, vm)
    }
}
val Context.dataStore by preferencesDataStore(
    name = "profile_picture"
)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProfilePicture(
    name: String = "name",
    onImageSelected: (Uri?) -> Unit = {}
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val dataStore = context.dataStore
    val imageUriKey = stringPreferencesKey("image_uri")
    val scope = rememberCoroutineScope()

    // Load image URI from DataStore on launch
    LaunchedEffect(0) {
        dataStore.data.collect { preferences ->
            val uri= preferences[imageUriKey]?.let { Uri.parse(it) }



        }

    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        onImageSelected(uri)
        Log.d("datStore", selectedImageUri.toString())

        // Save image URI to DataStore
        scope.launch {
            dataStore.edit { preferences ->
                preferences[imageUriKey] = uri?.toString() ?: "" // Handle null URI
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Log.d("datStore", selectedImageUri.toString())
            val imageToDisplay = selectedImageUri
                ?: "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Flower_poster_2.jpg/495px-Flower_poster_2.jpg"

            AsyncImage(
                model = imageToDisplay,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_android_black_24dp),
                contentDescription = "The design logo",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )

            IconButton(onClick = { launcher.launch("image/*") }) {
                Icon(Icons.Default.Create, contentDescription = "Select Image")
            }
        }
    }
}


@Composable
fun AchievementsList(achievements: List<Achievement> = listOf(),
                     streak: String = "-") {
Row(Modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceEvenly) {
Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Button(onClick = {}) {
        Text(text = "$streak  ")
        Icon(ImageVector.vectorResource(R.drawable.fire_icon), "")
    }
    Text(text = "Streak")
}
Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
    Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)) {
        Text(text = "?  ")
        Icon(ImageVector.vectorResource(R.drawable.star_icon),"")
    }
    Text(text = "Achievements")
}



}
}


@Composable
fun HivesGrid(hives: List<HiveDbEntity>, inTabNavigation: NavHostController, vm: MainViewModel) {
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(minSize = 128.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(hives+hives) { hive ->
//            HiveItem(hive, inTabNavigation, vm)
//        }
//    }

    LazyColumn {
        items(hives) { hive ->
            HiveItem(hive, inTabNavigation, vm)
        }
    }
}

@Composable
fun HiveItem(hive: HiveDbEntity, inTabNavigation: NavHostController, vm: MainViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(1.dp)
            , onClick = {
                vm.currentHive.value = hive
                inTabNavigation.navigate(TabNavRote.HiveScreen.rote)
        }
    ) {
        Column(Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(
                text = hive.name,
                fontSize = 25.sp,
            )

        }
    }
}


data class User(
    val name: String,
    val level: Int,
    val streak: String,
    val achievements: List<Achievement>,
    val hives: List<HiveDbEntity>
)

data class Achievement(
    val title: String,
    val description: String
)

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ProfileScreenPrev() {
    //ProfileScreen(vm)
}