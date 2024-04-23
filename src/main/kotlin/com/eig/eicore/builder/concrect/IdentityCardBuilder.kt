package com.eig.eicore.builder.concrect

import com.eig.eicore.builder.ModelConcrectBuilder
import com.eig.eicore.model.concrect.IdentityCard
import com.eig.eicore.model.concrect.Person
import java.time.LocalDate

class IdentityCardBuilder (
    val identityCard: IdentityCard = IdentityCard()
): ModelConcrectBuilder<IdentityCard>(identityCard){

    fun person(person: Person) = apply{ identityCard.person = person }

    fun emittedIn(emittedIn: String) = apply{ identityCard.emittedIn = emittedIn }

    fun emittedAt(emittedAt: LocalDate) = apply{ identityCard.emittedAt = emittedAt }

    fun validAt(validAt: LocalDate) = apply{ identityCard.validAt = validAt }

    fun residential(residential: String) = apply { identityCard.residential  = residential }

}
