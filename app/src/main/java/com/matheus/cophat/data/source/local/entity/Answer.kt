package com.matheus.cophat.data.source.local.entity

enum class Answer(val chosenAnswer: String, val chosenAnswerPoints: Int) {
    ANSWER_01("Nunca", 0),
    ANSWER_02("Quase nunca", 1),
    ANSWER_03("Às vezes", 2),
    ANSWER_04("Quase sempre", 3),
    ANSWER_05("Sempre", 4),
    ANSWER_06("Não", 0),
    ANSWER_07("Sim", 1)
}