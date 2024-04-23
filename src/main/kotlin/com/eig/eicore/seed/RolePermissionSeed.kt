package com.eig.eicore.seed

import com.eig.eicore.builder.pivot.RolePermissionBuilder
import com.eig.eicore.model.concrect.Permission
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.model.pivot.RolePermission

enum class RolePermissionSeed(
    var role: Role,
    var permission: Permission
) {

    ROLE_VIEW(RoleSeed.ROLE.role, PermissionSeed.ROLE_VIEW.permission),
    ROLE_SAVE(RoleSeed.ROLE.role, PermissionSeed.ROLE_SAVE.permission),
    ROLE_UPDATE(RoleSeed.ROLE.role, PermissionSeed.ROLE_UPDATE.permission),
    ROLE_DELETE(RoleSeed.ROLE.role, PermissionSeed.ROLE_DELETE.permission),

    PERMISSION_VIEW(RoleSeed.PERMISSION.role, PermissionSeed.PERMISSION_VIEW.permission),
    PERMISSION_SAVE(RoleSeed.PERMISSION.role, PermissionSeed.PERMISSION_SAVE.permission),
    PERMISSION_UPDATE(RoleSeed.PERMISSION.role, PermissionSeed.PERMISSION_UPDATE.permission),
    PERMISSION_DELETE(RoleSeed.PERMISSION.role, PermissionSeed.PERMISSION_DELETE.permission),

    PERSON_VIEW(RoleSeed.PERSON.role, PermissionSeed.PERSON_VIEW.permission),
    PERSON_SAVE(RoleSeed.PERSON.role, PermissionSeed.PERSON_SAVE.permission),
    PERSON_UPDATE(RoleSeed.PERSON.role, PermissionSeed.PERSON_UPDATE.permission),
    PERSON_DELETE(RoleSeed.PERSON.role, PermissionSeed.PERSON_DELETE.permission),

    IDENTITY_CARD_VIEW(RoleSeed.IDENTITY_CARD.role, PermissionSeed.IDENTITY_CARD_VIEW.permission),
    IDENTITY_CARD_SAVE(RoleSeed.IDENTITY_CARD.role, PermissionSeed.IDENTITY_CARD_SAVE.permission),
    IDENTITY_CARD_UPDATE(RoleSeed.IDENTITY_CARD.role, PermissionSeed.IDENTITY_CARD_UPDATE.permission),
    IDENTITY_CARD_DELETE(RoleSeed.IDENTITY_CARD.role, PermissionSeed.IDENTITY_CARD_DELETE.permission);

    fun toRolePermission(): RolePermission = RolePermissionBuilder()
        .role(role)
        .permission(permission)
        .build()

}