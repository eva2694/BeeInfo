package si.uni_lj.fe.mis.tobeeornottobee.navigate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import si.uni_lj.fe.mis.tobeeornottobee.MainViewModel
import si.uni_lj.fe.mis.tobeeornottobee.R
import si.uni_lj.fe.mis.tobeeornottobee.screens.HomeScreen
import si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.HiveScreen
import si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.addHiveScreens.cmera.AddQrScanScreen
import si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.addHiveScreens.list.AddHiveListScreen
import si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.addHiveScreens.map.MapMainScreen
import si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.profile.ProfileScreen
import si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.study.quiz.QuizScreen

sealed class TabNavRote(val rote: String){

    object HomeScreen: TabNavRote("home")
    object AddMapScreen: TabNavRote("add_map_screen")
    object AddListScreen: TabNavRote("add_list_screen")
    object AddQrScanScreen: TabNavRote("add_qr_scan_screen")

    object HiveScreen: TabNavRote("hive_screen")

    object FunFactsScreen: TabNavRote("fun_facts_screen")
    object QuizScreen: TabNavRote("quiz_screen")
    object ProfileScreen: TabNavRote("profile_screen")





}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabNavHost(inTabNavigation: NavHostController) {
    val vm : MainViewModel = hiltViewModel()
    var selectedBottomTab by remember {
        mutableStateOf(2)
    }
    var selectedTopTab by remember {
        mutableStateOf(0)
    }
    var topBarState by remember {
        mutableStateOf(false)
    }
    var searchText by remember { mutableStateOf("") }  // State to hold the text field value


    Scaffold(
        topBar = {

            TopAppBar({
                AnimatedVisibility(topBarState){
                    TopAppBar(title = { }, actions = {
                        NavigationBarItem(
                            selected = selectedTopTab==1,
                            onClick = {
                                inTabNavigation.navigate(TabNavRote.AddMapScreen.rote)
                                selectedTopTab =1 },
                            icon = { Icon(Icons.Default.LocationOn, contentDescription ="" ) })
                        NavigationBarItem(
                            selected = selectedTopTab==2,
                            onClick = { selectedTopTab =2
                                inTabNavigation.navigate(TabNavRote.AddQrScanScreen.rote)
                            },
                            icon = { Icon(ImageVector.vectorResource(R.drawable.baseline_qr_code_scanner_24), contentDescription ="" ) })
                        NavigationBarItem(
                            selected = selectedTopTab==3,
                            onClick = { selectedTopTab =3
                                inTabNavigation.navigate(TabNavRote.AddListScreen.rote)
                            },
                            icon = { Icon(Icons.Default.Search, contentDescription ="" ) })
                    })
                }

            })


        },
        bottomBar = {
            BottomAppBar(windowInsets = BottomAppBarDefaults.windowInsets) {


                NavigationBarItem(selected = selectedBottomTab==1,
                    onClick = {
                    selectedBottomTab=1
                    topBarState = false
                    inTabNavigation.navigate(TabNavRote.QuizScreen.rote)
                              },
                    icon = { Icon(ImageVector.vectorResource(id = R.drawable.book_icon), contentDescription ="" )

                 },
                    label = {
                        Text(text = "quiz")
                    })


                Spacer(Modifier.weight(1f, true))

                NavigationBarItem(selected = selectedBottomTab==2, onClick = {
                    topBarState = false
                    selectedBottomTab=2
                    inTabNavigation.navigate(TabNavRote.ProfileScreen.rote)

                },                    icon = { Icon(ImageVector.vectorResource(id = R.drawable.home_icon), contentDescription ="" )
                },
                    label = { Text(text = "account")})
            }
        },
        floatingActionButton = {
            Box() {
                FloatingActionButton(
                    onClick = {
                        selectedBottomTab =3
                        selectedTopTab = 1
                        topBarState = true
                        inTabNavigation.navigate(TabNavRote.AddMapScreen.rote)

                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                        .offset(y = 50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null,
                        modifier = Modifier.size(45.dp)
                    )
                }
            }
        },


        floatingActionButtonPosition = FabPosition.Center,


        ) {paddingValues ->

        NavHost(navController = inTabNavigation, startDestination = TabNavRote.ProfileScreen.rote) {
            composable(route = TabNavRote.ProfileScreen.rote) {
                HomeScreen(inTabNavigation, paddingValues, vm)
            }
            composable(route = TabNavRote.AddMapScreen.rote) {
                MapMainScreen(inTabNavigation,paddingValues, vm)
            }
            composable(route = TabNavRote.AddListScreen.rote) {
                AddHiveListScreen(inTabNavigation,paddingValues, vm)
            }
            composable(route = TabNavRote.AddQrScanScreen.rote) {
                AddQrScanScreen(inTabNavigation,paddingValues)
            }
            composable(route = TabNavRote.HiveScreen.rote) {
                HiveScreen(inTabNavigation,paddingValues, vm)
            }
            composable(route = TabNavRote.FunFactsScreen.rote) {
               // FunFactsScreen(inTabNavigation,paddingValues)
            }
            composable(route = TabNavRote.QuizScreen.rote) {
                QuizScreen(inTabNavigation,paddingValues)
            }

            composable(route = TabNavRote.ProfileScreen.rote) {
                ProfileScreen(vm, inTabNavigation,paddingValues)
            }



        }


    }

}