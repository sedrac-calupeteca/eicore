package com.eig.eicore.factory

import com.eig.eicore.builder.concrect.RoleBuilder
import com.eig.eicore.enumeration.RoleType
import com.eig.eicore.model.concrect.Role

class RoleFactory {
    companion object{

        fun build(name: String, description: String, roleType: RoleType = RoleType.SYSTEM) : Role = RoleBuilder()
            .name("ROLE_$name")
            .description(description)
            .roleType(roleType)
            .build()

    }
}