package si.uni_lj.fe.mis.tobeeornottobee.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import si.uni_lj.fe.mis.tobeeornottobee.R
import si.uni_lj.fe.mis.tobeeornottobee.di.QuizViewModel

@Composable
fun QuizScreen(navController: NavHostController) {
    val viewModel = viewModel<QuizViewModel>()

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    val questions = remember { viewModel.getQuestions() }
    val currentQuestion = questions[currentQuestionIndex]

    var userAnswer by remember { mutableStateOf<Int?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display question
        Text(
            text = currentQuestion.text,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display image
        Image(
            painter = painterResource(id = R.drawable.placeholder_image),
            contentDescription = "Bee Image",
            modifier = Modifier
                .size(330.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))


        // Display answer options
        currentQuestion.options.forEachIndexed { index, option ->
            val buttonColor  = if (userAnswer == index) {
                if (index == currentQuestion.correctIx) {
                    Color.Green
                } else Color.Red
            } else Color(android.graphics.Color.parseColor("#428af5"))

            Button(
                onClick = { userAnswer = index },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(buttonColor)
            ) {
                Text(
                    text = option,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    if (currentQuestionIndex >= questions.size - 1) {
                        if (userAnswer != null && userAnswer == currentQuestion.correctIx) {
                            score++
                        }
                        showDialog = true
                    } else {
                        if (userAnswer != null && userAnswer == currentQuestion.correctIx) {
                            score++
                        }
                        currentQuestionIndex++
                        userAnswer = null
                    }
                }
            ) {
                Text(text = if (currentQuestionIndex < questions.size - 1) "Next" else "Get Results")
            }


        }

        if (showDialog) {
            Dialog(
                onDismissRequest = {
                    showDialog = false
                    navController.popBackStack()
                },
                properties = DialogProperties(dismissOnClickOutside = false)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your score: $score / ${questions.size}",
                        fontSize = 25.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { showDialog = false; navController.popBackStack() }) {
                        Text(text = "OK")
                    }
                }
            }
        }
    }



}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizScreenPreview() {
    val navController = rememberNavController()
    QuizScreen(navController = navController)
}
