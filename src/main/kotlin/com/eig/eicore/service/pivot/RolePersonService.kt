package com.eig.eicore.service.pivot


import com.eig.eicore.exception.NotFoundResourceException
import com.eig.eicore.model.concrect.Person
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.model.pivot.RolePerson
import com.eig.eicore.repository.pivot.RolePersonRepository
import org.springframework.stereotype.Service

@Service
class RolePersonService(
    val rolePersonRepository: RolePersonRepository
) {

    fun findByRoleAndPerson(role: Role, person: Person) : RolePerson = rolePersonRepository.findByRoleAndPerson(role, person).orElseGet { throw NotFoundResourceException("Role[${role.name}] and Person[${person.username}] not found in RolePerson") }

    fun saveOrUpdate(rolePerson: RolePerson): RolePerson {
        rolePersonRepository.findByRoleAndPerson(rolePerson.role, rolePerson.person).ifPresent { rolePerson.id = it.id }
        return rolePersonRepository.save(rolePerson)
    }


}