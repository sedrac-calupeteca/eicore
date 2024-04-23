package com.eig.eicore.model.concrect

import com.eig.eicore.enumeration.RoleType
import com.eig.eicore.model.ModelConcrect
import jakarta.persistence.*

@Entity
@Table(name = "tb_roles")
class Role (
    @Column(unique = true) var name: String,
    var description: String,
    @Enumerated(EnumType.STRING) var roleType: RoleType = RoleType.APPLICATION,
): ModelConcrect(){

    constructor(): this("","")

    override fun toString(): String = "Role(name='$name', description='$description', roleType=$roleType)"

    override fun concatValuesFields() = setConcatFields("$name,$description,$roleType")

}