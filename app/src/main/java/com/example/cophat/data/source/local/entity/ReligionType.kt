package com.example.cophat.data.source.local.entity

enum class ReligionType(religion: String, religionPoints: Int) {
    CATHOLIC("Católica", 1),
    EVANGELICAL("Evangélica", 2),
    SPIRITIST("Espírita", 3),
    OTHER("Outra", 4),
    NONE("Nenhuma", 5)
}