package com.matheus.cophat.data.local.entity

import com.google.firebase.database.Exclude

data class Applicator(
    var name: String = "",
    var contact: String = ""
) {
    override fun toString(): String {
        return name
    }

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "contact" to contact
        )
    }
}