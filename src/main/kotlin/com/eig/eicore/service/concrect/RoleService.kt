package com.eig.eicore.service.concrect

import com.eig.eicore.exception.NotFoundResourceException
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.repository.concrect.RoleRepository
import com.eig.eicore.service.ConcrectService
import org.springframework.stereotype.Service

@Service
class RoleService(
    val roleRepository: RoleRepository
): ConcrectService<Role>(roleRepository){

    fun findByName(name: String): Role = roleRepository.findByName(name).orElseThrow { throw NotFoundResourceException("name[$name] not found in Role") }

    fun saveOrUpdate(role: Role): Role {
        roleRepository.findByName(role.name).ifPresent { role.id = it.id }
        return  roleRepository.save(role)
    }

}