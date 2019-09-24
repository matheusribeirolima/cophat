package com.matheus.cophat.data.local.entity

enum class SubAnswerType(val chosenSubAnswer: String, val chosenSubAnswerPoints: Int) {
    SUB_ANSWER_01("Raiva", 1),
    SUB_ANSWER_02("Medo", 1),
    SUB_ANSWER_03("Tristeza", 1),
    SUB_ANSWER_04("Cansaço", 1),
    SUB_ANSWER_05("Falta de apetite", 1),
    SUB_ANSWER_06("Enjoo", 1),
    SUB_ANSWER_07("Dor", 1),
    SUB_ANSWER_08("Tontura", 1),
    SUB_ANSWER_09("Fraqueza", 1),
    SUB_ANSWER_10("Sono", 1),
    SUB_ANSWER_11("Irritação", 1),
    SUB_ANSWER_12("Dificuldade de atenção", 1),
    SUB_ANSWER_13("Suor", 1),
    SUB_ANSWER_14("Fraqueza", 1),
    SUB_ANSWER_15("Inchaço", 1),
    SUB_ANSWER_16("Perda de peso", 1),
    SUB_ANSWER_17("Ganho de peso", 1),
    SUB_ANSWER_18("Queda de cabelo", 1),
    SUB_ANSWER_19("Marcas de injeção / agulha", 1),
    SUB_ANSWER_20("Manchas", 1),
    SUB_ANSWER_21("Perda de partes do corpo", 1),
    SUB_ANSWER_22("Palidez", 1),
    SUB_ANSWER_23("Olho amarelo", 1),
    SUB_ANSWER_24("Caroços", 1),
    SUB_ANSWER_25("Ter cateter ou fistula", 1),
    SUB_ANSWER_26("Tristeza por causa disso", 1),
    SUB_ANSWER_27("Dificuldade motora", 1),
    SUB_ANSWER_OTHER("Outros", 1)
}

data class SubAnswer(
    val chosenSubAnswerType: String,
    val chosenSubAnswerPoints: Int,
    var other: String? = null
)