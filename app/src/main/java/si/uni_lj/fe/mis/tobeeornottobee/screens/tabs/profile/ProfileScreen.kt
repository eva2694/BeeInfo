package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import si.uni_lj.fe.mis.tobeeornottobee.R
import si.uni_lj.fe.mis.tobeeornottobee.model.hives.room.HiveDbEntity

@Composable
fun ProfileScreen() {

    val user= User("some",1,3,
        achievements = listOf(
            Achievement("streak","1 week"),
            Achievement("streak","1 week"),
        Achievement("streak","1 week"),
    Achievement("streak","1 week")),hives = listOf(
        HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),

            HiveDbEntity(name = "name"),

            HiveDbEntity(name = "name"),

            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),
            HiveDbEntity(name = "name"),


    ))
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ProfilePicture(user.name)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "name: ${user.level}",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "streak: ${user.streak} days",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "My Achievements:",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        AchievementsList(user.achievements)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "My Hives:",
            style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        HivesGrid(user.hives)
    }
}

@Composable
fun ProfilePicture(name: String) {
    val imageUrl =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Flower_poster_2.jpg/495px-Flower_poster_2.jpg"

    AsyncImage(
        model = imageUrl,
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        error = painterResource(id = R.drawable.ic_android_black_24dp),
        contentDescription = "The delasign logo",
    )



}



@Composable
fun AchievementsList(achievements: List<Achievement>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(achievements) { achievement ->
            AchievementItem(achievement)
        }
    }
}

@Composable
fun AchievementItem(achievement: Achievement) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(26.dp),

    ) {
        Column {
            Text(
                text = achievement.title,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = achievement.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun HivesGrid(hives: List<HiveDbEntity>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(hives) { hive ->
            HiveItem(hive)
        }
    }
}

@Composable
fun HiveItem(hive: HiveDbEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(0.5f).padding(16.dp),
    ) {
        Column {
            Text(
                text = hive.name,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }
}


data class User(
    val name: String,
    val level: Int,
    val streak: Int,
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
    ProfileScreen()
}