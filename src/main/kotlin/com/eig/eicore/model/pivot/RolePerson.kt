package com.eig.eicore.model.pivot

import com.eig.eicore.model.ModelPivot
import com.eig.eicore.model.concrect.Person
import com.eig.eicore.model.concrect.Role
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tb_role_person")
class RolePerson(
    @ManyToOne var role: Role,
    @ManyToOne var person: Person,
) : ModelPivot(){

    constructor(): this(Role(), Person())

    override fun toString(): String = "RolePerson(role=$role, person=$person)"

    override fun concatValuesFields() = setConcatFields("${role.name},${person.username},${role.id},${person.id}")

}