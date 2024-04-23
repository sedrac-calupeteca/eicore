package com.eig.eicore.builder.pivot

import com.eig.eicore.builder.ModelCommonBuilder
import com.eig.eicore.model.concrect.Person
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.model.pivot.RolePerson

class RolePersonBuilder(
   private val rolePerson: RolePerson = RolePerson()
): ModelCommonBuilder<RolePerson>(rolePerson) {

    fun role(role: Role) = apply{ rolePerson.role = role }

    fun person(person: Person) = apply{ rolePerson.person = person }

}