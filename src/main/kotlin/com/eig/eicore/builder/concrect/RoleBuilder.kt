package com.eig.eicore.builder.concrect

import com.eig.eicore.builder.ModelConcrectBuilder
import com.eig.eicore.enumeration.RoleType
import com.eig.eicore.model.concrect.Role

class RoleBuilder(
    val role: Role = Role()
): ModelConcrectBuilder<Role>(role) {

    fun name(name: String) = apply{ role.name = name }

    fun description(description: String) = apply{ role.description = description }

    fun roleType(roleType: RoleType) = apply{ role.roleType = roleType }

}