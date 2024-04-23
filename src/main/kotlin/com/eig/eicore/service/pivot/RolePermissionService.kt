package com.eig.eicore.service.pivot


import com.eig.eicore.exception.NotFoundResourceException
import com.eig.eicore.model.concrect.Permission
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.model.pivot.RolePermission
import com.eig.eicore.repository.pivot.RolePermissionRepository
import org.springframework.stereotype.Service

@Service
class RolePermissionService(
    val rolePermissionRepository: RolePermissionRepository
) {

    fun findByRoleAndPermission(role: Role, permission: Permission) : RolePermission = rolePermissionRepository.findByRoleAndPermission(role, permission).orElseGet { throw NotFoundResourceException("Role[${role.name}] and Permission[${permission.name}] not found in RolePermission") }

    fun saveOrUpdate(rolePermission: RolePermission): RolePermission {
        rolePermissionRepository.findByRoleAndPermission(rolePermission.role, rolePermission.permission).ifPresent { rolePermission.id = it.id }
        return rolePermissionRepository.save(rolePermission)
    }


}