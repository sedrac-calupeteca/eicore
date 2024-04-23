package com.eig.eicore.service

import com.eig.eicore.model.ModelConcrect
import com.eig.eicore.repository.ModelConcrectRepository
import java.time.LocalDateTime

open class ConcrectService <T: ModelConcrect> (
    private val repositoryConcrect : ModelConcrectRepository<T>
): CommonService<T>(repositoryConcrect) {

    override fun delete(entity: T){
        deleteById(entity.id)
    }

    override fun deleteById(id: String){
        val entity = findById(id)
        authenticationFindId().ifPresent { entity.deletedBy = it }
        entity.deletedAt = LocalDateTime.now()
        repositoryConcrect.save(entity)
    }

}