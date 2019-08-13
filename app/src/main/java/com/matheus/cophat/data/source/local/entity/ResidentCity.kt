package com.matheus.cophat.data.source.local.entity

enum class ResidentCity(val liveInThisCity: String, val liveInThisCityPoints: Int) {
    YES("Sim", 1),
    NO("Não", 0)
}