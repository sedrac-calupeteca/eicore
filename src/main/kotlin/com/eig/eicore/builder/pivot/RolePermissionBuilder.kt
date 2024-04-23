package com.eig.eicore.builder.pivot

import com.eig.eicore.builder.ModelCommonBuilder
import com.eig.eicore.model.concrect.Permission
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.model.pivot.RolePermission

class RolePermissionBuilder(
    private val rolePermission: RolePermission = RolePermission()
): ModelCommonBuilder<RolePermission>(rolePermission) {

    fun role(role: Role) = apply{ rolePermission.role = role }

    fun permission(permission: Permission) = apply{ rolePermission.permission = permission }

}