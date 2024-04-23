package com.eig.eicore.factory

import com.eig.eicore.builder.concrect.PermissionBuilder
import com.eig.eicore.enumeration.EntityEnum
import com.eig.eicore.model.concrect.Permission


class PermissionFactory {

    companion object{

        fun build(name: String, description: String, entity: EntityEnum = EntityEnum.NONE) : Permission = PermissionBuilder()
            .name("PERMISSION_$name")
            .description(description)
            .entity(entity.name)
            .build()

    }

}