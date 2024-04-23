package com.eig.eicore.repository.concrect

import com.eig.eicore.model.concrect.Person
import com.eig.eicore.repository.ModelConcrectRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PersonRepository : ModelConcrectRepository<Person> {

    @Query(name = "SELECT p FROM Person p WHERE username = :username WHERE deletedBy IS NULL AND deletedAt IS NULL")
    fun findByUsername(username: String): Optional<Person>

}