package com.eig.eicore.repository.pivot

import com.eig.eicore.model.concrect.Person
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.model.pivot.RolePerson
import com.eig.eicore.repository.ModelCommonRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RolePersonRepository: ModelCommonRepository<RolePerson> {
    fun findByRoleAndPerson(role: Role, person: Person): Optional<RolePerson>
}