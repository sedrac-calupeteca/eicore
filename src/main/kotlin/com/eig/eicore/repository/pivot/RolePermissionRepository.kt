package com.eig.eicore.repository.pivot

import com.eig.eicore.model.concrect.Permission
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.model.pivot.RolePermission
import com.eig.eicore.repository.ModelCommonRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RolePermissionRepository: ModelCommonRepository<RolePermission> {
    fun findByRoleAndPermission(role: Role, permission: Permission): Optional<RolePermission>

    @Query(name = "Person.existRolePermissionByPersonUsernameAndPermissionName")
    fun existsRolePermissionByPersonUsernameAndPermissionName(username: String, name: String) : Boolean

}