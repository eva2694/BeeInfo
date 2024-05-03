package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.funFactScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FunFactsScreen(
) {
    val fanFactsVM : FanFactsVM= hiltViewModel()
    val facts = fanFactsVM.readFactsFromXml()

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var fact by remember {
           mutableStateOf(facts.random())
        }
        // Відображення поточного факту
        Text(
            text = fact.title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = fact.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Кнопка для зміни факту
        Button(
            onClick = { fact= facts.random() },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "change fact")
        }
    }


}

@Preview
@Composable
private fun FactPrev() {
    FunFactsScreen(
    )
}

