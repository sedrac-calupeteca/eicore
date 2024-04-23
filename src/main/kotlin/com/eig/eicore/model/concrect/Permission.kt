package com.eig.eicore.model.concrect

import com.eig.eicore.model.ModelConcrect
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "tb_permissions")
class Permission(
    @Column(unique = true) var name: String,
    var description: String,
    var entity: String,
): ModelConcrect(){

    constructor(): this("","","")

    override fun toString(): String = "Permission(name='$name', description='$description', entity='$entity')"

    override fun concatValuesFields() = setConcatFields("$name,$description,$entity")

}