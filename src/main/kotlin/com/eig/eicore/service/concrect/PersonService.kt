package com.eig.eicore.service.concrect

import com.eig.eicore.exception.NotFoundResourceException
import com.eig.eicore.model.concrect.Person
import com.eig.eicore.repository.concrect.PersonRepository
import com.eig.eicore.service.ConcrectService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class PersonService(
    val personRepository: PersonRepository
): ConcrectService<Person>(personRepository) {

    fun findByUsername(username: String): Person = personRepository.findByUsername(username).orElseGet { throw NotFoundResourceException("Username[$username] not found in Person") }


    fun saveOrUpdate(person: Person): Person {
        personRepository.findByUsername(person.username).ifPresent {
            person.id = it.id
            person.password = BCryptPasswordEncoder().encode(person.password)
        }
        return  personRepository.save(person)
    }

}