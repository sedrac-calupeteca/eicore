package com.eig.eicore.builder

import com.eig.eicore.model.ModelConcrect
import java.time.LocalDateTime

abstract class ModelConcrectBuilder<T: ModelConcrect >  (
   private val entityConcrect: T
): ModelCommonBuilder<T>(entityConcrect){

    fun deletedAt(deletedAt: LocalDateTime = LocalDateTime.now()) = apply { entityConcrect.deletedAt = deletedAt }

    fun deletedBy(deletedBy: String? = null) = apply { entityConcrect.deletedBy = deletedBy }

}