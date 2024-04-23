package com.eig.eicore.builder

import com.eig.eicore.model.ModelCommon
import java.time.LocalDateTime
import java.util.Optional

abstract class ModelCommonBuilder<T: ModelCommon >  (
    private val entity: T
){

    fun id(id: String? = null) = apply{ Optional.ofNullable(id).ifPresent { entity.id = it }   }

    fun createdAt(createdAt: LocalDateTime = LocalDateTime.now()) = apply { entity.createdAt = createdAt }

    fun updatedAt(updatedAt: LocalDateTime = LocalDateTime.now()) = apply { entity.updatedAt = updatedAt }

    fun createdBy(createdBy: String? = null) = apply { entity.createdBy = createdBy }

    fun updatedBy(updatedBy: String? = null) = apply { entity.updatedBy = updatedBy }

    fun concatFields(concatFields: String = "") = apply { entity.concatFields = concatFields }

    fun build(): T = entity

}