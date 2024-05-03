package si.uni_lj.fe.mis.tobeeornottobee.model

data class Question(
    val text: String,
    val options: List<String>,
    val correctIx: Int
)

