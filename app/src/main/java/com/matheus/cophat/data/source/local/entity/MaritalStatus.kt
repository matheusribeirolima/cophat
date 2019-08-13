package com.matheus.cophat.data.source.local.entity

enum class MaritalStatus(maritalStatus: String, maritalStatusPoints: Int) {
    MARRIED("Casado", 1),
    AMASSED("Amasiado", 2),
    DIVORCED_SEPARATED("Divorciado/Separado", 3),
    SINGLE("Solteiro", 4),
    WIDOWER("Viúvo", 5)
}