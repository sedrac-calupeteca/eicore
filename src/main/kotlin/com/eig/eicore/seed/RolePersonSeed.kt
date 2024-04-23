package com.eig.eicore.seed

import com.eig.eicore.builder.pivot.RolePersonBuilder
import com.eig.eicore.model.concrect.Person
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.model.pivot.RolePerson


enum class RolePersonSeed(
    var role: Role,
    var person: Person
) {
    SUPER_SUPER(RoleSeed.SUPER.role, PersonSeed.SUPER.person),
    ROLE_ROLE(RoleSeed.ROLE.role, PersonSeed.ROLE.person),
    PERMISSION_PERMISSION(RoleSeed.PERMISSION.role, PersonSeed.PERMISSION.person),
    PERSON_PERSON(RoleSeed.PERSON.role, PersonSeed.PERSON.person),
    IDENTITY_CARD(RoleSeed.IDENTITY_CARD.role, PersonSeed.IDENTITY_CARD.person);

    fun toRolePerson(): RolePerson = RolePersonBuilder()
        .role(role)
        .person(person)
        .build()

}