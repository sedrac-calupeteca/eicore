package com.eig.eicore.repository.concrect


import com.eig.eicore.model.concrect.IdentityCard
import com.eig.eicore.model.concrect.Person
import com.eig.eicore.repository.ModelConcrectRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IdentityCardRepository : ModelConcrectRepository<IdentityCard> {

    @Query(name = "SELECT i FROM IdentityCard i WHERE person = :person AND deletedBy IS NULL AND deletedAt is NULL")
    fun findByPerson(person: Person): Optional<IdentityCard>
}