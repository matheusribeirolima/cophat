package com.matheus.cophat.data.local.entity

import com.google.firebase.database.Exclude

data class Questionnaire(
    var familyId: String = "",
    var childApplication: ApplicationEntity? = null,
    var parentApplication: ApplicationEntity? = null
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "familyId" to familyId,
            "childApplication" to childApplication,
            "parentApplication" to parentApplication
        )
    }
}