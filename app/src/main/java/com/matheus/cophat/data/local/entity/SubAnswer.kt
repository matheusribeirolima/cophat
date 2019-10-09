package com.matheus.cophat.data.local.entity

enum class SubAnswerType(val chosenSubAnswer: String, val chosenSubAnswerPoints: Int) {
    RAGE("Raiva", 1),
    FEAR("Medo", 1),
    SADNESS("Tristeza", 1),
    TIREDNESS("Cansaço", 1),
    APPETITE_LACK("Falta de apetite", 1),
    SICK("Enjoo", 1),
    PAIN("Dor", 1),
    DIZZINESS("Tontura", 1),
    WEAKNESS("Fraqueza", 1),
    SLEEP("Sono", 1),
    IRRITATION("Irritação", 1),
    ATTENTION_DIFFICULTY("Dificuldade de atenção", 1),
    SWEAT("Suor", 1),
    MOTOR_DIFFICULTY("Dificuldade motora", 1),
    OTHER("Outros", 1),
    SWELLING("Inchaço", 1),
    WEIGHT_LOSS("Perda de peso", 1),
    WEIGHT_GAIN("Ganho de peso", 1),
    HAIR_LOSS("Queda de cabelo", 1),
    INJECTION_BRANDS("Marcas de injeção / agulha", 1),
    STAINS("Manchas", 1),
    BODY_PARTS_LOSS("Perda de partes do corpo", 1),
    PALLOR("Palidez", 1),
    YELLOW_EYE("Olho amarelo", 1),
    LAMPS("Caroços", 1),
    CATHETER("Ter cateter ou fistula", 1),
    SADNESS_BECAUSE("Tristeza por causa disso", 1),
    OPEN("Alternativa aberta", 1),
    PILL("Comprimido", 1),
    INJECTION("Injeção", 1),
    VEIN("Na veia", 1)
}

data class SubAnswer(
    var type: SubAnswerType? = null,
    var description: String? = type?.chosenSubAnswer,
    var points: Int? = type?.chosenSubAnswerPoints,
    var other: String? = null
)