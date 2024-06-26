package si.uni_lj.fe.mis.tobeeornottobee.screens.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import si.uni_lj.fe.mis.tobeeornottobee.model.hives.room.HiveDbEntity


//"https://images.unsplash.com/photo-1504392022767-a8fc0771f239?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
@Composable
fun MyHiveItem(
    isMyHiveScreen: Boolean= true,
    hive: HiveDbEntity,
    onAddClick: ()->Unit,
    onClick:  ()->Unit) {
    Card(modifier = Modifier.padding(10.dp), onClick = {onClick()}) {


        Row(
            Modifier
                .fillMaxWidth()
                .padding(3.dp), verticalAlignment = Alignment.CenterVertically) {

            Column(Modifier.weight(0.7f), horizontalAlignment = Alignment.End) {
                Row {
                Row(Modifier.fillMaxWidth().weight(0.5f, fill = true),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = hive.name, fontSize = 20.sp)}
                Row(Modifier.fillMaxWidth().weight(0.15f, fill = false),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End) {
                    if (!isMyHiveScreen){
                   Column(Modifier.height(IntrinsicSize.Min)) {
                       IconButton(

                           onClick = { onAddClick() }) {
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
                           Text(text = "collected", maxLines = 1)
                       else Text(text = "collect", maxLines = 1)
                   }
                   }


                }
                    }




            }

        }







    }


}

@Preview
@Composable
private fun itemHP() {
    Column {
        MyHiveItem(false,
            HiveDbEntity(imageURL ="https://images.unsplash.com/photo-1504392022767-a8fc0771f239?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                name = "some name" ),
            {}
        ){

        }
        MyHiveItem(false,
                HiveDbEntity(imageURL ="https://images.unsplash.com/photo-1504392022767-a8fc0771f239?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    name = "some name",
                    isCollect = true),
        {}
        ){

    }
        MyHiveItem(true,
            HiveDbEntity(imageURL ="https://images.unsplash.com/photo-1504392022767-a8fc0771f239?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                name = "some name",
                isCollect = true),
            {}
        ){

        }
    }
}
