package com.matheus.cophat.data.local.entity

data class Applicator(
    var name: String = "",
    var contact: String = ""
) {
    override fun toString(): String {
        return name
    }
}