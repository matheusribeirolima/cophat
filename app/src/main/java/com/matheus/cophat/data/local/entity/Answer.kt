package com.matheus.cophat.data.local.entity

enum class AnswerType(val chosenAnswer: String, val chosenAnswerPoints: Int) {
    NEVER("Nunca", 0),
    ALMOST_NEVER("Quase nunca", 1),
    SOMETIMES("Às vezes", 2),
    OFTEN("Quase sempre", 3),
    ALWAYS("Sempre", 4),
    NO("Não", 0),
    YES("Sim", 1)
}

data class Answer(
    var id: Int? = null,
    var chosenAnswer: AnswerType? = null,
    var subAnswers: HashMap<String, SubAnswer>? = null
)