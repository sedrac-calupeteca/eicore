package com.eig.eicore.builder.concrect

import com.eig.eicore.builder.ModelConcrectBuilder
import com.eig.eicore.enumeration.Gender
import com.eig.eicore.enumeration.MaritalStatus
import com.eig.eicore.model.concrect.Person
import java.time.LocalDate

class PersonBuilder(
    val person: Person = Person()
): ModelConcrectBuilder<Person>(person) {

    fun fullname(fullname: String) = apply{ person.fullname = fullname }

    fun fullnameFather(fullnameFather: String) = apply{ person.fullnameFather = fullnameFather }

    fun fullnameMother(fullnameMother: String) = apply{ person.fullnameMother = fullnameMother }

    fun identityCardNumber(identityCardNumber: String) = apply{ person.identityCardNumber = identityCardNumber }

    fun username(username: String) = apply{ person.username = username }

    fun email(email: String) = apply{ person.email = email }

    fun password(password: String) = apply{ person.password = password }

    fun gender(gender: Gender) = apply{ person.gender = gender }

    fun maritalStatus(maritalStatus: MaritalStatus) = apply{ person.maritalStatus = maritalStatus }

    fun birthday(birthday: LocalDate) = apply{ person.birthday = birthday }

    fun naturalFrom(naturalFrom: String) = apply{ person.naturalFrom = naturalFrom }


}