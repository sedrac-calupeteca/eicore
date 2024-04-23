package com.eig.eicore.service.concrect

import com.eig.eicore.exception.NotFoundResourceException
import com.eig.eicore.model.concrect.Permission
import com.eig.eicore.repository.concrect.PermissionRepository
import com.eig.eicore.service.ConcrectService
import org.springframework.stereotype.Service

@Service
class PermissionService(
    val permissionRepository: PermissionRepository
): ConcrectService<Permission>(permissionRepository) {

    fun findByName(name: String): Permission = permissionRepository.findByName(name).orElseThrow { throw NotFoundResourceException("name[$name] not found in Permission") }

    fun saveOrUpdate(permission: Permission): Permission {
        permissionRepository.findByName(permission.name).ifPresent { permission.id = it.id }
        return  permissionRepository.save(permission)
    }


}