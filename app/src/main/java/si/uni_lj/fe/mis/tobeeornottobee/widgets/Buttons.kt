package si.uni_lj.fe.mis.tobeeornottobee.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import si.uni_lj.fe.mis.tobeeornottobee.R

@Preview
@Composable
fun MyButton(modifier: Modifier = Modifier) {
    Column {

        val drawable: Painter = painterResource(id = R.drawable.fire_icon)
        Image(painter = drawable, contentDescription = null)

        Button(modifier = modifier, onClick = {},
            colors = ButtonDefaults.buttonColors().copy(containerColor = MaterialTheme.colorScheme.primary)) {
            Text("qqq")
        }
    }


}