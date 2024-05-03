package si.uni_lj.fe.mis.tobeeornottobee.screens.tabs.study.quiz

import androidx.lifecycle.ViewModel
import si.uni_lj.fe.mis.tobeeornottobee.model.Question

class QuizViewModel : ViewModel() {
    private val allQuestions: List<Question> = listOf(
        Question(
            text = "What is the name of the process by which bees collect pollen?",
            options = listOf("Pollination", "Nectar collection", "Foraging"),
            correctIx = 0 // Pollination
        ),
        Question(
            text = "Which species of bee is responsible for pollinating flowers?",
            options = listOf("Honeybee", "Bumblebee", "Carpenter bee"),
            correctIx = 0 // Honeybee
        ),
        Question(
            text = "What is the lifespan of a worker bee during the summer season?",
            options = listOf("4 weeks", "6 weeks", "8 weeks"),
            correctIx = 1 // 6 weeks
        ),
        Question(
            text = "What is the function of the queen bee in a hive?",
            options = listOf("Lay eggs", "Collect nectar", "Protect the hive"),
            correctIx = 0 // Lay eggs
        ),
        Question(
            text = "How many wings does a bee have?",
            options = listOf("Two", "Four", "Six"),
            correctIx = 2 // Six
        ),
        Question(
            text = "What substance do bees use to build their hives?",
            options = listOf("Wax", "Mud", "Grass"),
            correctIx = 0 // Wax
        ),
        Question(
            text = "What is the purpose of the waggle dance performed by honeybees?",
            options = listOf("To communicate the location of food sources", "To warn about predators", "To signal the queen's presence"),
            correctIx = 0 // To communicate the location of food sources
        ),
        Question(
            text = "Which of the following is NOT a role of worker bees?",
            options = listOf("Laying eggs", "Foraging for nectar", "Building honeycomb"),
            correctIx = 0 // Laying eggs
        ),
        Question(
            text = "How many eyes do bees have?",
            options = listOf("Two", "Three", "Five"),
            correctIx = 1 // Three
        ),
        Question(
            text = "What is the purpose of the bee's stinger?",
            options = listOf("To inject venom into attackers", "To collect pollen", "To communicate with other bees"),
            correctIx = 0 // To inject venom into attackers
        ),
        Question(
            text = "Which bee species is known for its solitary nature?",
            options = listOf("Leafcutter bee", "Honeybee", "Bumblebee"),
            correctIx = 0 // Leafcutter bee
        ),
        Question(
            text = "What is the main component of honey?",
            options = listOf("Sugars", "Proteins", "Fats"),
            correctIx = 0 // Sugars
        ),
        Question(
            text = "What is the function of propolis in a beehive?",
            options = listOf("To seal cracks and crevices", "To store honey", "To feed larvae"),
            correctIx = 0 // To seal cracks and crevices
        )
    )

    private val selectedQuestions: MutableList<Question> = mutableListOf()

    init {
        // Select 3 random questions from allQuestions
        selectedQuestions.addAll(allQuestions.shuffled().take(3))
    }

    fun getQuestions(): List<Question> {
        return selectedQuestions
    }
}
