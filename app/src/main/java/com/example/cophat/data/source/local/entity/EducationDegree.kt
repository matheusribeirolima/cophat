package com.example.cophat.data.source.local.entity

enum class EducationDegree(val educationDegree: String, val educationDegreePoints: Int) {
    ILLITERATE("Analfabeto / Fundamental I incompleto", 1),
    COMPLETE_FUNDAMENTAL_I("Fundamental I completo / Fundamental II incompleto", 2),
    COMPLETE_FUNDAMENTAL_II("Fundamental II completo / Médio incompleto", 3),
    COMPLETE_MEDIUM("Médio completo / Superior incompleto", 4),
    GRADUATED("Superior completo", 5)
}