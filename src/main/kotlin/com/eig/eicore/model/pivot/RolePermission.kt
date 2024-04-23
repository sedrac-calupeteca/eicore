package com.eig.eicore.model.pivot

import com.eig.eicore.model.ModelPivot
import com.eig.eicore.model.concrect.Permission
import com.eig.eicore.model.concrect.Role
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tb_role_permission")
class RolePermission(
    @ManyToOne var role: Role,
    @ManyToOne var permission: Permission,
): ModelPivot(){

    constructor(): this(Role(),Permission())

    override fun toString(): String = "RolePermission(role=$role, permission=$permission)"

    override fun concatValuesFields() = setConcatFields("${role.name},${permission.name},${role.id},${permission.id}")

}