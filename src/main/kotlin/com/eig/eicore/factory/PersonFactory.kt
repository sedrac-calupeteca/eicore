package com.eig.eicore.factory

import com.eig.eicore.builder.concrect.PersonBuilder
import com.eig.eicore.enumeration.Gender
import com.eig.eicore.enumeration.MaritalStatus
import com.eig.eicore.model.concrect.Person
import java.time.LocalDate

class PersonFactory {

    companion object{

        fun build(name: String, identityCardNumber: String) : Person =  PersonBuilder()
            .fullname("$name FULLNAME")
            .fullnameFather("$name FATHER")
            .fullnameMother("$name MOTHER")
            .username("${name.lowercase()}-slc")
            .email("${name.lowercase()}@eiauth.com")
            .identityCardNumber(identityCardNumber)
            .password("12345678")
            .birthday(LocalDate.now().minusYears(20))
            .gender(Gender.MALE)
            .maritalStatus(MaritalStatus.SINGLE)
            .naturalFrom("Luanda")
            .build()

    }

}