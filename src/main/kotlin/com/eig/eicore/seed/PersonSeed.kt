package com.eig.eicore.seed

import com.eig.eicore.factory.PersonFactory
import com.eig.eicore.model.concrect.Person

enum class PersonSeed(val person: Person) {
    SUPER(PersonFactory.build("SUPER", "005610433EG0390")),
    PERSON(PersonFactory.build("PERSON", "005610433EG040")),
    ROLE(PersonFactory.build("ROLE", "005610433EG041")),
    PERMISSION(PersonFactory.build("PERMISSION", "005610433EG042")),
    IDENTITY_CARD(PersonFactory.build("IDENTITY_CARD", "005610433EG043"))
}