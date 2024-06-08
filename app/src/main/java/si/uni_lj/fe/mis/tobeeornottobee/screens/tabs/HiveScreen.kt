package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs

import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import coil.compose.AsyncImage
import com.google.android.gms.maps.model.LatLng
import si.uni_lj.fe.mis.tobeeornottobee.MainViewModel
import si.uni_lj.fe.mis.tobeeornottobee.R
import si.uni_lj.fe.mis.tobeeornottobee.screens.items.MyMapItem
import kotlin.random.Random

const val steps = 10
@Composable
fun HiveScreenOld(navController: NavHostController,
               paddingValues: PaddingValues,
               viewModel: MainViewModel?

               ) {
    val hive = viewModel?.currentHive?.observeAsState()!!.value!!
    val isCollected = viewModel?.currentHive?.observeAsState()!!.value!!.isCollect


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row (
            Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Column {
                IconButton(modifier = Modifier.weight(1f, fill = false), onClick = {
                    viewModel?.updateHive(hive.copy(isCollect = true))
                    viewModel.currentHive.value = hive.copy(isCollect = true)
                }) {
                    if (hive.isCollect)
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Default.CheckCircle,
                            tint = Color.Green,
                            contentDescription = ""
                        )
                    else
                        Icon(
                            modifier = Modifier.fillMaxSize(),
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = ""
                        )
                }
                if (hive.isCollect)
                    Text(text = "collected")
                else Text(text = "collect")
            }
Column {
    IconButton(onClick = { viewModel?.updateHive(hive.copy(isCollect = false))
        viewModel.currentHive.value = hive.copy(isCollect = false)

    }) {
        Icon(modifier= Modifier.fillMaxSize(),imageVector = Icons.Default.Delete, contentDescription = "")
    }
    Text(text = "Delete")
}


        }
        Spacer(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimaryContainer)
                .fillMaxWidth()
                .size(2.dp)

        )
        Row(Modifier.weight(1f)) {
            AsyncImage(

                model = hive.imageURL,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                error = painterResource(id = R.drawable.ic_android_black_24dp),
                contentDescription = "The delasign logo",
            )
            Text(
                text = hive.name,
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column {
                Text(
                    text = "bee count:  ${hive.beeCount}",
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "humidity:  ${hive.humidity} %",
                    style = TextStyle(fontSize = 16.sp)
                )
                Text(
                    text = "temperature:  ${hive.temperature} ℃",
                    style = TextStyle(fontSize = 16.sp)
                )
            }


        }
        MyMapItem(Modifier.weight(1f),marker = LatLng(hive.latitude,hive.longitude))






    }
}



@Composable
fun ProgressBar() {
    var progress by remember { mutableStateOf(0.5f) }
    LinearProgressIndicator(progress = progress)
}

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HiveScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: MainViewModel?

) {
    val hive = viewModel?.currentHive?.observeAsState()!!.value!!

//    val hive =HiveDbEntity(name = "hive 1",
//        imageURL = "https://images.unsplash.com/photo-1504392022767-a8fc0771f239?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
//        latitude =1.35,
//        longitude = 103.87,
//        humidity = 50.9f,
//        temperature = 36.6f,
//        beeCount = 65000,
//        isCollect = true,
//        voc = 0
//    )
        val scroollState = rememberScrollState()

     Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(scroollState)

    ) {

             MyMapItem(modifier = Modifier
                 .fillMaxWidth()
                 .clip(RoundedCornerShape(16.dp) )
                 .height(300.dp)
                 ,marker = LatLng(hive.latitude,hive.longitude))




        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(8.dp))
         Row {
             Button(
                 onClick = { /* Handle click */ },
                 modifier = Modifier.weight(fill = true, weight = 0.5f),
                 shape = RoundedCornerShape(8.dp)
             ) {
                 Text(text = hive.name)
             }
             IconButton(modifier = Modifier, onClick = {
                 viewModel?.updateHive(hive.copy(isCollect = true))
                 viewModel.currentHive.value = hive.copy(isCollect = true)
             }) {
                 if (hive.isCollect)
                     Icon(
                         modifier = Modifier.fillMaxSize(),
                         imageVector = Icons.Default.CheckCircle,
                         tint = Color.Green,
                         contentDescription = ""
                     )
                 else
                     Icon(
                         modifier = Modifier.fillMaxSize(),
                         imageVector = Icons.Default.AddCircle,
                         contentDescription = ""
                     )
             }
             IconButton(onClick = { viewModel?.updateHive(hive.copy(isCollect = false))
                 viewModel.currentHive.value = hive.copy(isCollect = false)

             }) {
                 Icon(modifier= Modifier.fillMaxSize(),imageVector = Icons.Default.Delete, contentDescription = "")
             }
         }

        InfoCard(
            label = "location",
            value = "${hive.latitude},${hive.longitude}",
            color = Color(0xFFFFBD33),
            vectorResource = ImageVector.vectorResource(R.drawable.location_info_icon)
        )
        Spacer(modifier = Modifier.height(8.dp))
        InfoCard(label = "Bee Count", value = "${hive.beeCount}", color = Color(0xFF1B5E20),
            ImageVector.vectorResource(R.drawable.count_info_icon))

        Spacer(modifier = Modifier.height(8.dp))
        InfoCard(
            label = "Humidity",
            value = "${hive.humidity} %",
            color = Color(0xFF1E88E5),
            vectorResource = ImageVector.vectorResource(R.drawable.humidity)
        )
        Spacer(modifier = Modifier.height(8.dp))
        InfoCard(
            label = "temperature",
            value = "${hive.temperature} ℃",
            color = Color(0xFFDbd0300),
            vectorResource = ImageVector.vectorResource(R.drawable.temperature_icon)
        )
    }
}

@Composable
fun InfoCard(label: String, value: String, color: Color, vectorResource: ImageVector) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {

            Icon(vectorResource,"",modifier = Modifier.padding(start = 5.dp))
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = label,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
                Text(
                    text = value,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        }

}

@Composable
fun Graph() {
    val pointsData = getPoints()
    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
        .backgroundColor(Color.Blue)
        .steps(pointsData.size - 1)
        .labelData { i -> i.toString() }
        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        .backgroundColor(Color.Green)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            (i*10).toString()
        }.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(),
                    IntersectionPoint(),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(),
        backgroundColor = Color.White
    )
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HivePrev() {
    val navController = rememberNavController()
    HiveScreen(navController, PaddingValues(),null)


}


fun getPoints(): List<Point>
{
    val list = ArrayList<Point>()
    for (i in 0..31){
        list.add(
            Point(
            i.toFloat(),
            Random.nextInt(0,100).toFloat()
        )
        )
    }
    return list
}
