package com.matheus.cophat.data.local.entity

enum class ResidentCityType(val liveInThisCity: String, val liveInThisCityPoints: Int) {
    YES("Sim", 1),
    NO("Não", 0)
}

data class ResidentCity(val liveInThisCity: String, val liveInThisCityPoints: Int)