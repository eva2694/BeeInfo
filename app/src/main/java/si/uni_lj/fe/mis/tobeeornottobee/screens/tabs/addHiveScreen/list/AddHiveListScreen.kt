package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.addHiveScreen.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
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
   val hives = vm.allHives.observeAsState().value

LazyColumn(Modifier.padding(paddingValues)) {

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