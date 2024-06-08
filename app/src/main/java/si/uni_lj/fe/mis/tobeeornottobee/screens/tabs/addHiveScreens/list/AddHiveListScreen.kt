package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.addHiveScreens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import si.uni_lj.fe.mis.tobeeornottobee.MainViewModel
import si.uni_lj.fe.mis.tobeeornottobee.navigate.TabNavRote
import si.uni_lj.fe.mis.tobeeornottobee.screens.items.MyHiveItem

@Composable
fun AddHiveListScreen(
    inTabNavigation: NavHostController,
    paddingValues: PaddingValues,
    vm: MainViewModel
) {



Column(Modifier.padding(paddingValues)) {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        }
    )
    val hives = vm.allHives.observeAsState().value?.filter { it.name.contains(text) }



    LazyColumn() {

        items(hives?: listOf(),){
            MyHiveItem(
                isMyHiveScreen = false,
                hive = it, onAddClick = { vm.updateHive(it.copy(isCollect = true)) })
            {
                vm.currentHive.value = it
                inTabNavigation.navigate(TabNavRote.HiveScreen.rote)


            }
        }

    }

}

}