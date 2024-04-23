package com.eig.eicore.service

import com.eig.eicore.exception.BadRequestDataException
import com.eig.eicore.exception.NotFoundResourceException
import com.eig.eicore.exception.PermissionDeniedException
import com.eig.eicore.model.ModelCommon
import com.eig.eicore.repository.ModelCommonRepository
import com.eig.eicore.seed.PermissionSeed
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import java.time.LocalDateTime
import java.util.Optional


open class CommonService<T: ModelCommon> (
    private val repositoryCommon: ModelCommonRepository<T>
)  {

    protected fun authenticationFindId() : Optional<String> = Optional.ofNullable(SecurityContextHolder.getContext().authentication.name)

    fun canPermission(permissionSeed: PermissionSeed){
        val isPermission = Optional.ofNullable(SecurityContextHolder.getContext().authentication.authorities).map {
            it.any { i -> i.authority.equals(permissionSeed.permission.name) }
        }.orElse(false)
        if (!isPermission) throw PermissionDeniedException("Permission ${permissionSeed.permission.name} denied")
    }

    fun findAll(pageable: Pageable): Page<T> = repositoryCommon.findAll(pageable)

    fun findById(id: String): T = repositoryCommon.findById(id).orElseThrow { NotFoundResourceException() }

    fun save(entity: T): T{
        authenticationFindId().ifPresent { entity.createdBy = it }
        entity.createdAt = LocalDateTime.now()
        return  Optional.ofNullable(repositoryCommon.save(entity)).orElseThrow{throw BadRequestDataException() }
    }

    fun update(entity: T): T {
        repositoryCommon.findById(entity.id).ifPresent { entity.id = it.id }
        authenticationFindId().ifPresent { entity.updatedBy = it }
        entity.updatedAt = LocalDateTime.now()
        return  Optional.ofNullable(repositoryCommon.save(entity)).orElseThrow{throw BadRequestDataException() }
    }

    open fun delete(entity: T){
        deleteById(entity.id)
    }

    open fun deleteById(id: String){
        findById(id)
        repositoryCommon.deleteById(id)
    }

}