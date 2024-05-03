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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
fun HiveScreen(navController: NavHostController,
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
