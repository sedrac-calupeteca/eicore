package com.eig.eicore.model

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
abstract class ModelCommon(
    @Id @UuidGenerator var id: String = "",
    var createdAt: LocalDateTime? = LocalDateTime.now(),
    var updatedAt: LocalDateTime? = LocalDateTime.now(),
    var createdBy: String? = "",
    var updatedBy: String? = "",
    @JvmField @Column(columnDefinition="TEXT", unique= true) var concatFields: String = ""
): Serializable {

    @PrePersist
    protected fun prePersist() { concatValuesFields() }

    @PreUpdate
    protected fun preUpdate(){ concatValuesFields() }

    protected fun setConcatFields(concat: String = ""){ concatFields = concat }

    abstract fun concatValuesFields()
}

@MappedSuperclass
abstract class ModelConcrect(
    var deletedAt: LocalDateTime? = null,
    var deletedBy: String? = null,
): ModelCommon()

@MappedSuperclass
abstract class ModelPivot() : ModelCommon()