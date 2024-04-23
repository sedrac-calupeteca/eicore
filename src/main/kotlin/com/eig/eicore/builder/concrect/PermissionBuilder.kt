package com.eig.eicore.builder.concrect

import com.eig.eicore.builder.ModelConcrectBuilder
import com.eig.eicore.model.concrect.Permission

class PermissionBuilder (
    val permission: Permission = Permission()
): ModelConcrectBuilder<Permission>(permission){

    fun name(name: String) = apply{ permission.name = name }

    fun entity(entity: String) = apply{ permission.entity = entity }

    fun description(description: String) = apply{ permission.description = description }

}
